chrome.browserAction.setIcon({ path: { "16": "icons/grey-16.png" } })

var isShoppingPage;
var ethicliStats;
var productName;

chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
        reloadExt(request, sender, sendResponse)
    }
);
chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
      if (request.msgName == "displayOptin") {
        chrome.browserAction.setPopup({ popup: "popupOptin.html" })
        chrome.browserAction.setIcon({ path: { "16": "icons/grey-16.png" }})
        chrome.browserAction.setBadgeText({ text: "" });
      }
    }
);

function reloadExt(request, sender, sendResponse) {
    if (request.msgName == "PageEvaluated") {
        var query = { active: true, currentWindow: true };
        chrome.tabs.query(query, function callback(tabs) {
            var currentTab = tabs[0];

            if (request.shoppingPage == true) {
                chrome.browserAction.setIcon({ path: { "16": "icons/ethicli-16.png" }, tabId: currentTab.id })
                isShoppingPage = true;

                var companyName = urlToCompanyName(sender.tab.url);

                var blacklist = ["google", "bing", "yahoo",  "baidu", "aol", "duckduckgo", "yandex", "ecosia"];
                var notBlacklisted;
                var ethicliBadgeScore;
                for (b=0; b<blacklist.length; b++) {
                    if (companyName.includes(blacklist[b])) {
                        ethicliBadgeScore = "";
                        notBlacklisted = false;
                        break;
                    }
                    else {
                        notBlacklisted = true;
                    }
                }

                if (notBlacklisted) {
                    var companyRequest = new XMLHttpRequest()
                    var url = 'https://ethicli.com/score/' + companyName;
                    companyRequest.open('GET', url, true)
                    companyRequest.onload = function() {
                        var jsonResponse = JSON.parse(this.response);
                        ethicliStats = jsonResponse;
                        ethicliBadgeScore = Math.round(jsonResponse.overallScore);

                        if ((isNaN(jsonResponse.overallScore)) || (ethicliBadgeScore == 0)) {
                            ethicliBadgeScore = "";
                            chrome.browserAction.setPopup({ popup: "popupNoRating.html", tabId: currentTab.id })
                            reportGA("ShopNoRating");
                        } else {
                            chrome.browserAction.setPopup({ popup: "popup.html", tabId: currentTab.id })
                            reportGA("ShopHasRating");
                        }
                        chrome.browserAction.setBadgeText({ text: ethicliBadgeScore.toString(), tabId: currentTab.id });
                    }
                    companyRequest.send();
                }
                else {
                  notShop();
                }
            }
            else {
              notShop();
            }

            function notShop(){
              reportGA("NotShop");
              isShoppingPage = false;
              chrome.browserAction.setPopup({ popup: "popupNotShop.html", tabId: currentTab.id })
              chrome.browserAction.setIcon({ path: { "16": "icons/grey-16.png" }, tabId: currentTab.id })
              chrome.browserAction.setBadgeText({ text: "", tabId: currentTab.id });
            }
          })
    }
    return true;
}

chrome.tabs.onActivated.addListener(
    function() {
        var query = { active: true, currentWindow: true };
        chrome.tabs.query(query, function callback(tabs) {
            var currentTab = tabs[0];
            chrome.tabs.sendMessage(tabs[0].id, { msgName: "isShoppingPage?" }, function(response) {
              // On first page visit, response is null. This function is only supposed to run after the first visit anyway,
              // so this just gets rid of an error that didn't actually break anything
              if (response) {
                isShoppingPage = response.isShoppingPage;
                // currentTab.url is null for new tab pages when first opened and equal to "chrome://newtab/" when navigated to from another tab.
                if (isShoppingPage && currentTab.url && (currentTab.url != "chrome://newtab/")) {
                // data should only be retrieved for actual pages that are shopping pages
                    chrome.tabs.sendMessage(tabs[0].id, { msgName: "productIdentified?" }, function(response) {
                        productName = response.productName
                    })
                    var request = { msgName: "PageEvaluated", shoppingPage: true };
                    var sender = { tab: { url: "" } };
                    sender.tab.url = currentTab.url;
                    reloadExt(request, sender);
                }
              }
            });
        })
    }
);

function urlToCompanyName(url) {
  if (url.substring(0, 8) == "https://") {
    url = url.substring(8);
  }
  else if (url.substring(0, 7) == "http://") {
    url = url.substring(7);
  }
  var endOfBaseDomain = url.search(/\//);
  if (endOfBaseDomain > -1) {
    url = url.substring(0, endOfBaseDomain);
  }
  var endOfSubDomain = url.lastIndexOf('.', url.lastIndexOf('.')-1)
  url = url.substring(endOfSubDomain+1);
  endOfBaseDomain = url.search(/\./);
  if (endOfBaseDomain > -1) {
    url = url.substring(0, endOfBaseDomain);
  }
  return url;
}

chrome.tabs.onCreated.addListener(
    function() {
        var query = { active: true, currentWindow: true };
        chrome.tabs.query(query, function callback(tabs) {
            var currentTab = tabs[0];
            chrome.browserAction.setPopup({ popup: "popupNotShop.html", tabId: currentTab.id })
            chrome.browserAction.setIcon({ path: { "16": "icons/grey-16.png" }, tabId: currentTab.id })
            chrome.browserAction.setBadgeText({ text: "", tabId: currentTab.id });
        })
    }
)
chrome.tabs.onUpdated.addListener(
  function() {
    var query = { active: true, currentWindow: true };
    chrome.tabs.query(query, function callback(tabs) {
        var currentTab = tabs[0];
        chrome.tabs.sendMessage(currentTab.id, { msgName: "reevaluatePage" });
    })
  }
)

chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
        if (request.msgName == "ProductIdentified") {
            productName = request.productName;
        }
    }
);

chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
        if (request.msgName == "isShoppingPage?") {
            sendResponse({ shoppingPage: isShoppingPage });
        }
        if (request.msgName == "whatsMainRating?") {
            sendResponse({ ethicliStats: ethicliStats });
        }
        if (request.msgName == "productIdentified?") {
            sendResponse({ productName: productName });
        }
    }
);

// onInstalled has a details.reason, for the next update we need to use this value
// to ensure this only runs when details.reason == "install"
chrome.runtime.onInstalled.addListener(
  function handleInstalled(details) {
    let welcomeTabId;

    if (details.reason == "install") {
      chrome.tabs.create({url: "https://ethicli.com/welcome"}, function(result) {
        welcomeTabId = result.id;

        chrome.tabs.onUpdated.addListener(
          function() {
            var query = { active: true, currentWindow: true };
            chrome.tabs.query(query, function callback(tabs) {
                var currentTab = tabs[0];
                if (currentTab.id  === welcomeTabId) {
                  chrome.tabs.sendMessage(currentTab.id, { msgName: "isEthicliWelcomePage" });
                }
            })
          }
        )
      })
    }
  }
);

// GOOGLE ANALYTICS
const GA_TRACKING_ID = "UA-173025073-1";
const GA_CLIENT_ID = "4FB5D5BF-B582-41AD-9BDF-1EC789AE6544";

function reportGA(aType) {
  try {
    let request = new XMLHttpRequest();
    let message =
      "v=1&tid=" + GA_TRACKING_ID + "&cid= " + GA_CLIENT_ID + "&aip=1" +
      "&ds=add-on&t=event&ec=VISITORS&ea=" + aType;
    request.open("POST", "https://www.google-analytics.com/collect", true);
    request.send(message);
  } catch (e) {
    console.error("Error sending report to Google Analytics.\n" + e);
  }
}
