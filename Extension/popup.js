chrome.runtime.sendMessage({ msgName: "isShoppingPage?" }, function(response) {
    if (response.shoppingPage == true) {
        loadExtension();
    }
});

function loadExtension() {
    chrome.runtime.sendMessage({ msgName: "whatsMainRating?" }, function(response) {
        var ethicliScore = (response.ethicliStats.overallScore / 20).toFixed(1);
        if (response.ethicliStats.bcorpCertified && response.ethicliStats.bluesignPartner) {
            // bluesign partners get an extra point added to their score
            ethicliScore = (response.ethicliStats.overallScore / 20 + 1).toFixed(1);
        } else if (!response.ethicliStats.bcorpCertified && response.ethicliStats.bluesignPartner) {
            ethicliScore = 7.5;
        }

        document.getElementById("overallScore").innerHTML = ethicliScore;
        if (response.ethicliStats.bcorpCertified) {
            document.getElementById("bcorp").classList.add("trueForPage");
        }
        if (response.ethicliStats.bluesignPartner) {
            document.getElementById("bluesign").classList.add("trueForPage");
        }
    });
}
