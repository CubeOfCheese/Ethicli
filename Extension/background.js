chrome.browserAction.setIcon({path : {"16":"icons/gray_icon16.png"}})

chrome.runtime.onMessage.addListener(
  function(request, sender, sendResponse) {
    if (request.shoppingPage == true) {
      chrome.browserAction.setIcon({path : {"16": "icons/get_started16.png"}})

      var companyName = sender.tab.title.split(' ')[0];
      var blueSignRequest = new XMLHttpRequest()
      var url = 'http://localhost:8080/score/' + companyName;
      blueSignRequest.open('GET', url, true)
      blueSignRequest.onload = function() {
        console.log(this.response);
        if (this.response == "true") {
          chrome.browserAction.setBadgeText({text:"Yay"});
        }
        else {
          chrome.browserAction.setBadgeText({text:"Poo"});
        }
      }
      blueSignRequest.send()
    }
    else {
      console.log(request.shoppingPage);
      chrome.browserAction.setIcon({path : {"16":"icons/gray_icon16.png"}})
      chrome.browserAction.setBadgeText({text:""});
    }
    sendResponse({acknowledge: "okay"});
});
