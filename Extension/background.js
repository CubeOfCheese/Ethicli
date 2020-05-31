chrome.browserAction.setIcon({ path: { "16": "icons/gray_icon16.png" } })

var isShoppingPage;

chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
      if (request.msgName == "PageEvaluated") {
        if (request.shoppingPage == true) {
            chrome.browserAction.setIcon({ path: { "16": "icons/get_started16.png" } })
            isShoppingPage = true;
            var companyName = sender.tab.title.split(' ')[0];
            var blueSignRequest = new XMLHttpRequest()
            var url = 'http://ethicli.com/score/' + companyName;
            blueSignRequest.open('GET', url, true)
            blueSignRequest.onload = function() {
                if (this.response == "true") {
                    chrome.browserAction.setBadgeText({ text: "Yay" });
                } else {
                    chrome.browserAction.setBadgeText({ text: "Poo" });
                }
            }
            blueSignRequest.send()
        } else {
            isShoppingPage = false;
            chrome.browserAction.setIcon({ path: { "16": "icons/gray_icon16.png" } })
            chrome.browserAction.setBadgeText({ text: "" });
        }
      }
    }
);

chrome.runtime.onMessage.addListener(
  function(request, sender, sendResponse) {
    if (request.msgName == "isShoppingPage?") {
      sendResponse({shoppingPage: isShoppingPage});
    }
});
