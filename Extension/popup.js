chrome.runtime.sendMessage({ msgName: "isShoppingPage?" }, function(response) {
    if (response.shoppingPage) {
        loadExtension();
    }
});

function loadExtension() {
    chrome.runtime.sendMessage({ msgName: "whatsMainRating?" }, function(response) {
        var ethicliScore;
        if(response.ethicliStats.overallScore>0){
            ethicliScore = (response.ethicliStats.overallScore / 20).toFixed(1)
        }else{
            ethicliScore = (response.ethicliStats.bcorpScore / 20).toFixed(1)
            if (response.ethicliStats.bcorpCertified && response.ethicliStats.bluesignPartner) {
                // bluesign partners get an extra point added to their score
                ethicliScore = (response.ethicliStats.bcorpScore / 20 + 1).toFixed(1);
            } else if (!response.ethicliStats.bcorpCertified && response.ethicliStats.bluesignPartner) {
                ethicliScore = 7.5;
            }
        }

        if(document.getElementById("overallScore")!== null){ //checks to see if ID even appears on page
            document.getElementById("overallScore").innerHTML = ethicliScore;
        }

        var bt = 0;
        console.log("BOB: "+response.ethicliStats.blackOwnedBusiness);
        console.log("BLM: "+response.ethicliStats.supportsBLM);
        if (response.ethicliStats.bcorpCertified) {
            document.getElementById("bcorp").classList.add("trueForPage");
            bt++;
        }
        if (response.ethicliStats.bluesignPartner) {
            document.getElementById("bluesign").classList.add("trueForPage");
            bt++;
        }
        if (response.ethicliStats.blackOwnedBusiness) {
            document.getElementById("blackowned").classList.add("trueForPage");
            bt++;
        }
        if (response.ethicliStats.supportsBLM) {
            document.getElementById("blmsupport").classList.add("trueForPage");
            bt++;
        }
        if(bt>0){
            document.getElementById("noBadge").style.display = "none";
            document.getElementById("hasBadge").style.display = "block";
            document.body.style = "height:190px;"
        }
    });
}

window.onload = function() {
    document.getElementById("menu").addEventListener("click", function() {
        document.getElementById("menuPanel").classList.toggle("menuClicked");
    });

    document.getElementById("menuBacking").addEventListener("click", function() {
        document.getElementById("menuPanel").classList.remove("menuClicked");
    });

    document.getElementById("somethingWrong").addEventListener("click", function() {
        somethingWrong();
    });
}

function somethingWrong() {
    alert("We've received your alert that there's something off with this page's scoring. Site recorded!");
    var query = { active: true, currentWindow: true };
    chrome.tabs.query(query, function callback(tabs) {
        var currentTab = tabs[0];
        var fetchUrlFeedback = "http://ethicli.com/feedback";
        let fetchData = {
            url: currentTab.url
        };
        let fetchParams = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(fetchData)
        }
        fetch(fetchUrlFeedback, fetchParams)
    });
}
