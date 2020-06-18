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
        var fetchUrlDataStore = "http://ethicli.com/feedback";
        var fetchUrlEmailer = "http://ethicli.com/feedbackEmailer";
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
        fetch(fetchUrlDataStore, fetchParams)
        fetch(fetchUrlEmailer, fetchParams)
    });
}
