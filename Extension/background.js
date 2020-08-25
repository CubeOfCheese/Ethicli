chrome.browserAction.setIcon({ path: { "16": "icons/grey-16.png" } })

var isShoppingPage;
var ethicliStats;

chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
        reloadExt(request, sender, sendResponse)
    }
);

function reloadExt(request, sender, sendResponse) {
    if (request.msgName == "PageEvaluated") {
        var query = { active: true, currentWindow: true };
        chrome.tabs.query(query, function callback(tabs) {
            var currentTab = tabs[0];
            chrome.storage.local.get("optIn", function(result) {
              if (!result.optIn) {
                chrome.browserAction.setPopup({ popup: "popupOptin.html", tabId: currentTab.id})
              }
              else {
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
                            } else {
                                chrome.browserAction.setPopup({ popup: "popup.html", tabId: currentTab.id })
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
                  isShoppingPage = false;
                  chrome.browserAction.setPopup({ popup: "popupNotShop.html", tabId: currentTab.id })
                  chrome.browserAction.setIcon({ path: { "16": "icons/grey-16.png" }, tabId: currentTab.id })
                  chrome.browserAction.setBadgeText({ text: "", tabId: currentTab.id });
                }
              }
            })
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

  if (url.substring(0, 4) == "www.") {
    url = url.substring(4);
  }
  else if (url.substring(0, 4) == "us.") {
    url = url.substring(3);
  }
  else if (url.substring(0, 4) == "docs.") {
    url = url.substring(5);
  }

  var endOfBaseDomain = url.search(/\./);
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

chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
        if (request.msgName == "isShoppingPage?") {
            sendResponse({ shoppingPage: isShoppingPage });
        }
        if (request.msgName == "whatsMainRating?") {
            sendResponse({ ethicliStats: ethicliStats });
        }
    }
);
