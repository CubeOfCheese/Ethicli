chrome.browserAction.setIcon({ path: { "16": "icons/gray_icon16.png" } })

var isShoppingPage;
var ethicliStats;

chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
      if (request.msgName == "PageEvaluated") {
        if (request.shoppingPage == true) {
            chrome.browserAction.setIcon({ path: { "16": "icons/get_started16.png" } })
            isShoppingPage = true;
            var companyNamePromise = getCompanyName(sender.tab.url)
            companyNamePromise.then(companyName=>{
              if (companyName == null) {
                var companyName = sender.tab.title.split(' ')[0];
              }
              var blueSignRequest = new XMLHttpRequest()
              var url = 'http://ethicli.com/score/' + companyName;
              blueSignRequest.open('GET', url, true)
              blueSignRequest.onload = function() {
                  var jsonResponse = JSON.parse(this.response);
                  ethicliStats = jsonResponse;

                  ethicliBadgeScore = Math.round(jsonResponse.overallScore/20);
                  if(isNaN(jsonResponse.overallScore)){
                    ethicliBadgeScore = 0;
                  }
                  if(jsonResponse.bcorpCertified && jsonResponse.bluesignPartner){
                      ethicliBadgeScore += 1;
                  }else if(jsonResponse.bluesignPartner){
                      ethicliBadgeScore = 7.5;
                  }
                  chrome.browserAction.setBadgeText({ text: ethicliBadgeScore.toString() });
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
            sendResponse({ shoppingPage: isShoppingPage });
        }
        if (request.msgName == "whatsMainRating?") {
            sendResponse({ ethicliStats: ethicliStats });
        }
    }
);

function getCompanyName(companyUrl) {
  if (companyUrl.substring(0, 8) == "https://") companyUrl = companyUrl.substring(8);
  else if (companyUrl.substring(0, 7) == "http://") companyUrl = companyUrl.substring(7);
  var endOfBaseDomain = companyUrl.search("/");
  if (endOfBaseDomain > -1) companyUrl = companyUrl.substring(0, endOfBaseDomain);
  var fetchUrl = "https://company.bigpicture.io/v1/companies/find?domain=" + companyUrl;
  var fetchParams = {
      headers: {
        'Authorization': env.COMPANY_NAME_API_KEY // hidden from github bots
      }
  }
  return fetch(fetchUrl, fetchParams)
  .then(data=>{return data.json()})
  .then(res=>{return res.name.toLowerCase();})
}
