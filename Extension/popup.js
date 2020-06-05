chrome.runtime.sendMessage({ msgName: "isShoppingPage?" }, function(response) {
    if (response.shoppingPage == true) {
        document.getElementById("shopping").classList.remove("notShop");
        document.getElementById("notShopMessage").classList.remove("notShop");
    } else {
        document.getElementById("shopping").classList.add("notShop");
        document.getElementById("notShopMessage").classList.add("notShop");
        document.body.style.height = "140px";
    }
});

window.onload = function() {
    chrome.runtime.sendMessage({ msgName: "whatsMainRating?" }, function(response) {
        var ethicliScore = (response.ethicliStats.overallScore/20).toFixed(1);
        console.log(ethicliScore);
        if(response.ethicliStats.bcorpCertified && response.ethicliStats.bluesignPartner){
            ethicliScore = (response.ethicliStats.overallScore/20).toFixed(1)+1;
        }else if(response.ethicliStats.bluesignPartner){
            ethicliScore = 7.5;
        }
        document.getElementById("overallScore").innerHTML = ethicliScore;
        if(response.ethicliStats.bcorpCertified){
            document.getElementById("bcorp").classList.add("trueForPage");
        }
        if(response.ethicliStats.bluesignPartner){
            document.getElementById("bluesign").classList.add("trueForPage");
        }
    });
}
somethingWrong()
function somethingWrong() {
  var query = { active: true, currentWindow: true };
  chrome.tabs.query(query, function callback(tabs) {
    var currentTab = tabs[0];
    console.log(currentTab.url);
    var fetchUrl = "http://ethicli.com/feedback/";
    var errorType;  // Needs to be implemented later. errorType will communicate
                    // wether the problem is a shop not being identified or a shop having the wrong data
    let fetchData = {
      url: currentTab.url,
      error: errorType
    };
    let fetchParams = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(fetchData)
    }
    fetch(fetchUrl, fetchParams)
  });
}
