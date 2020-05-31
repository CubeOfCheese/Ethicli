chrome.browserAction.setIcon({ path: { "16": "icons/gray_icon16.png" } })

var isShoppingPage;

chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
      if (request.msgName == "PageEvaluated") {
        if (request.shoppingPage == true) {
            chrome.browserAction.setIcon({ path: { "16": "icons/get_started16.png" } })
            isShoppingPage = true;
            var companyNamePromise = getCompanyName(sender.tab.url)
            companyNamePromise.then(companyName=>{
              console.log(companyName);
              // var companyName = sender.tab.title.split(' ')[0];
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
            });

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

function getCompanyName(companyUrl) {
  if (companyUrl.substring(0, 8) == "https://") companyUrl = companyUrl.substring(8);
  else if (companyUrl.substring(0, 7) == "http://") companyUrl = companyUrl.substring(7);
  if (companyUrl.substring(companyUrl.length-1) == "/") companyUrl = companyUrl.substring(0, companyUrl.length-1);
  console.log(companyUrl);
  var fetchUrl = "https://company.bigpicture.io/v1/companies/find?domain=" + companyUrl;
  var fetchParams = {headers: {
                        'Authorization': "1pKluzt6JcSoof1UMevh3G:4gzhFX6bwpGaFVv4C1WMjZ"
                      }
                    }
  return fetch(fetchUrl, fetchParams)
  .then(data=>{return data.json()})
  .then(res=>{return res.name})
}
