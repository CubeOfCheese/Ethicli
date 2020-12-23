import { getDomainWithoutSuffix } from "../node_modules/tldts-experimental/dist/index.esm.min.js";

chrome.browserAction.setIcon({ path: { "16": "icons/grey-16.png" } });

let isShoppingPage;
let ethicliStats;
let productName;

function notShop(currentTab) {
  console.log("NotSHop");
  isShoppingPage = false;
  chrome.browserAction.setPopup({ popup: "popupNotShop.html", tabId: currentTab.id });
  chrome.browserAction.setIcon({ path: { "16": "icons/grey-16.png" }, tabId: currentTab.id });
  chrome.browserAction.setBadgeText({ text: "", tabId: currentTab.id });
}

function reloadExt(request, sender) {
  console.log("reloadExt");
  const query = { active: true, currentWindow: true };
  chrome.tabs.query(query, (tabs) => {
    console.log(tabs);
    const currentTab = tabs[0];

    if (!request.shoppingPage) {
      console.log("notShop at 43");
      notShop(currentTab);
      return;
    }
    chrome.browserAction.setIcon({ path: { "16": "icons/ethicli-16.png" }, tabId: currentTab.id });
    isShoppingPage = true;

    const companyName = getDomainWithoutSuffix(sender.tab.url);

    const blocklist = [ "google", "bing", "yahoo", "baidu", "aol", "duckduckgo", "yandex", "ecosia" ];
    let isBlocklisted;
    let ethicliBadgeScore;
    for (let b = 0; b < blocklist.length; b++) {
      if (companyName.includes(blocklist[b])) {
        ethicliBadgeScore = "";
        isBlocklisted = true;
        break;
      } else {
        isBlocklisted = false;
      }
    }
    if (isBlocklisted) {
      console.log("notshop at blocklisted");
      notShop(currentTab);
      return;
    }

    const url = "https://ethicli.com/score/" + companyName;
    fetch(url, { method: "GET" })
        .then((response) => response.json()).then((jsonResponse) => {
          ethicliStats = jsonResponse;
          ethicliBadgeScore = Math.round(jsonResponse.overallScore);

          if ((isNaN(jsonResponse.overallScore)) || (ethicliBadgeScore === 0)) {
            ethicliBadgeScore = "";
            chrome.browserAction.setPopup({ popup: "popupNoRating.html", tabId: currentTab.id });
            reportGA("Background-NoRating");
          } else {
            chrome.browserAction.setPopup({ popup: "popup.html", tabId: currentTab.id });
            reportGA("Background-HasRating");
          }
          chrome.browserAction.setBadgeText({ text: ethicliBadgeScore.toString(), tabId: currentTab.id });
        });
  });
}

chrome.runtime.onMessage.addListener((request, sender, sendResponse) => {
  switch (request.msgName) {
    case "isShoppingPage?":
      sendResponse({ shoppingPage: isShoppingPage });
      break;
    case "whatsMainRating?":
      sendResponse({ ethicliStats: ethicliStats });
      break;
    case "productIdentified?":
      sendResponse({ productName: productName });
      break;
    case "ProductIdentified":
      productName = request.productName;
      break;
    case "PageEvaluated":
      reloadExt(request, sender);
      break;
    case "displayOptin":
      chrome.browserAction.setPopup({ popup: "popupOptin.html" });
      chrome.browserAction.setIcon({ path: { "16": "icons/grey-16.png" } });
      chrome.browserAction.setBadgeText({ text: "" });
      break;
    default:
      console.error(request, sender);
  }
});

chrome.tabs.onActivated.addListener(() => {
  const query = { active: true, currentWindow: true };
  chrome.tabs.query(query, (tabs) => {
    const currentTab = tabs[0];
    chrome.tabs.sendMessage(tabs[0].id, { msgName: "isShoppingPage?" }, (response) => {
      // On first page visit, response is null. This function is only supposed to run after the first visit anyway,
      // so this just gets rid of an error that didn't actually break anything
      if (response) {
        isShoppingPage = response.isShoppingPage;
        // currentTab.url is null for new tab pages when first opened
        // and equal to "chrome://newtab/" when navigated to from another tab.
        if (isShoppingPage && currentTab.url && (currentTab.url !== "chrome://newtab/")) {
          // data should only be retrieved for actual pages that are shopping pages
          chrome.tabs.sendMessage(tabs[0].id, { msgName: "productIdentified?" }, (response) => {
            productName = response.productName;
          });
          const request = { msgName: "PageEvaluated", shoppingPage: true };
          const sender = { tab: { url: "" } };
          sender.tab.url = currentTab.url;
          reloadExt(request, sender);
        }
      }
    });
  });
});

chrome.tabs.onCreated.addListener(() => {
  const query = { active: true, currentWindow: true };
  chrome.tabs.query(query, (tabs) => {
    const currentTab = tabs[0];
    chrome.browserAction.setPopup({ popup: "popupNotShop.html", tabId: currentTab.id });
    chrome.browserAction.setIcon({ path: { "16": "icons/grey-16.png" }, tabId: currentTab.id });
    chrome.browserAction.setBadgeText({ text: "", tabId: currentTab.id });
  });
});

chrome.tabs.onUpdated.addListener(() => {
  const query = { active: true, currentWindow: true };
  chrome.tabs.query(query, (tabs) => {
    const currentTab = tabs[0];
    chrome.tabs.sendMessage(currentTab.id, { msgName: "reevaluatePage" });
  });
});

chrome.runtime.onInstalled.addListener((details) => {
  if (details.reason !== "install") {
    return;
  }
  chrome.tabs.create({ url: "welcome.html" });
});

chrome.runtime.setUninstallURL("https://ethicli.com/goodbye");

// GOOGLE ANALYTICS
const GA_TRACKING_ID = "UA-173025073-1";
const GA_CLIENT_ID = "4FB5D5BF-B582-41AD-9BDF-1EC789AE6544";

function reportGA(aType) {
  try {
    const url = "https://www.google-analytics.com/collect";
    const message = `v=1&tid=${GA_TRACKING_ID}&cid=${GA_CLIENT_ID}&aip=1&ds=add-on&t=event&ec=VISITORS&ea=${aType}`;
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: message
    });
  } catch (e) {
    console.error("Error sending report to Google Analytics.\n" + e);
  }
}
