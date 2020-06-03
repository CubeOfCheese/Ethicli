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
        if(response.ethicliStats.bcorpCertified){
            if(response.ethicliStats.bluesignPartner){
                ethicliScore = (response.ethicliStats.overallScore/20).toFixed(1)+1;
            }
        }else{
            if(response.ethicliStats.bluesignPartner){
                ethicliScore = 7.5;
            }
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
