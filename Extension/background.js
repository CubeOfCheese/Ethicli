import config from "../config/config.js";
import { getDomainWithoutSuffix } from "tldts-experimental";
import mixpanel from "mixpanel-browser";
mixpanel.init(config.mixpanel_code, config.mixpanel_config);

chrome.browserAction.setIcon({ path: { "16": "icons/grey-16.png" } });

let ethicliStats;
let productName;

function notShop(currentTabId) {
  chrome.browserAction.setPopup({ popup: "views/popupNotShop.html", tabId: currentTabId });
  chrome.browserAction.setIcon({ path: { "16": "icons/grey-16.png" }, tabId: currentTabId });
  chrome.browserAction.setBadgeText({ text: "", tabId: currentTabId });
}
// use sender.tab.id for everything instead of currenttabid?
function reloadExt(request, sender) {
  // console.log("reloadExt" + new Date().getUTCSeconds() + " " + new Date().getMilliseconds());
  // console.log(sender.tab);
  // console.log("^sender tab");
  // console.log(request.shoppingPage);
  if (!request.shoppingPage) {
    notShop(sender.tab.id);
    return;
  }
  console.log("supposedly is shop");
  // TODO: retrieve from storage and set popup. If not found, then make api call
  chrome.storage.local.get([ sender.tab.id.toString() ], (jsonResponse) => {
    // if (chrome.runtime.lastError) {
    //   console.log("beep boop error");
    // }
    if (jsonResponse[sender.tab.id]) {
      jsonResponse = jsonResponse[sender.tab.id];
      console.log("found in storage");
      ethicliStats = jsonResponse;
      let ethicliBadgeScore = Math.round(jsonResponse.overallScore);
      console.log(jsonResponse.overallScore);
      if ((isNaN(jsonResponse.overallScore)) || (ethicliBadgeScore === 0)) {
        ethicliBadgeScore = "";
        chrome.browserAction.setPopup({ popup: "views/popupNoRating.html", tabId: sender.tab.id });
        mixpanel.track("Visit shop", {
          "Has score": false,
          "Shop words": request.shopWords
        });
      } else {
        console.log("Visit shop" + new Date().getUTCSeconds() + " " + new Date().getMilliseconds());
        chrome.browserAction.setPopup({ popup: "views/popupShop.html", tabId: sender.tab.id });
        chrome.storage.local.set({ [sender.tab.id]: jsonResponse });
        mixpanel.track("Visit shop", {
          "Has score": true,
          "Shop words": request.shopWords
        });
      }
      chrome.browserAction.setBadgeText({ text: ethicliBadgeScore.toString(), tabId: sender.tab.id });
    } else {
      console.log("not found in storage");
      const companyName = getDomainWithoutSuffix(sender.tab.url);

      // Feb 23 2021
      // etsy is included because we don't have a way to prevent matching of etsy with etsy.com/exampleShop
      // so etsy is awarded badges that should actually be attributed to exampleShop
      const blocklist = [
        "google",
        "bing",
        "yahoo",
        "baidu",
        "aol",
        "duckduckgo",
        "yandex",
        "ecosia",
        "etsy",
        "youtube",
        "facebook",
        "instagram"
      ];
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
        notShop(sender.tab.id);
        return;
      }

      const url = "https://info.ethicli.com/score/" + companyName;
      fetch(url, { method: "GET" })
          .then((response) => response.json()).then((jsonResponse) => {
            ethicliStats = jsonResponse;
            ethicliBadgeScore = Math.round(jsonResponse.overallScore);

            if ((isNaN(jsonResponse.overallScore)) || (ethicliBadgeScore === 0)) {
              ethicliBadgeScore = "";
              chrome.browserAction.setPopup({ popup: "views/popupNoRating.html", tabId: sender.tab.id });
              mixpanel.track("Visit shop", {
                "Has score": false,
                "Shop words": request.shopWords
              });
            } else {
              console.log("Visit shop" + new Date().getUTCSeconds() + " " + new Date().getMilliseconds());
              chrome.browserAction.setPopup({ popup: "views/popupShop.html", tabId: sender.tab.id });
              chrome.storage.local.set({ [sender.tab.id.toString()]: jsonResponse }, () => console.log("stored!"));
              mixpanel.track("Visit shop", {
                "Has score": true,
                "Shop words": request.shopWords
              });
            }
            chrome.browserAction.setBadgeText({ text: ethicliBadgeScore.toString(), tabId: sender.tab.id });
          });
    }
  });
  chrome.browserAction.setIcon({ path: { "16": "icons/ethicli-16.png" }, tabId: sender.tab.id });
}

chrome.runtime.onMessage.addListener((request, sender, sendResponse) => {
  switch (request.msgName) {
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
      chrome.browserAction.setPopup({ popup: "views/popupOptin.html" });
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
        // currentTab.url is null for new tab pages when first opened
        // and equal to "chrome://newtab/" when navigated to from another tab.
        if (response.isShoppingPage && currentTab.url && (currentTab.url !== "chrome://newtab/")) {
          // data should only be retrieved for actual pages that are shopping pages
          chrome.tabs.sendMessage(currentTab.id, { msgName: "productIdentified?" }, (response) => {
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
    const currentTabId = tabs[0].id;
    chrome.browserAction.setPopup({ popup: "views/popupNotShop.html", tabId: currentTabId });
    chrome.browserAction.setIcon({ path: { "16": "icons/grey-16.png" }, tabId: currentTabId });
    chrome.browserAction.setBadgeText({ text: "", tabId: currentTabId });
  });
});

// chrome.tabs.onUpdated.addListener(() => {
//   const query = { active: true, currentWindow: true };
//   chrome.tabs.query(query, (tabs) => {
//     const currentTab = tabs[0];
//     chrome.tabs.sendMessage(currentTab.id, { msgName: "reevaluatePage" });
//   });
// });

chrome.runtime.onInstalled.addListener((details) => {
  if (details.reason !== "install") {
    return;
  }
  chrome.tabs.create({ url: "views/welcome.html" });
});

chrome.runtime.setUninstallURL("https://ethicli.com/goodbye");
