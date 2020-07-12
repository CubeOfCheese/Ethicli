chrome.runtime.sendMessage({ msgName: "isShoppingPage?" }, function(response) {
    if (response.shoppingPage) {
        loadExtension();
    }
});

function loadExtension() {
    chrome.runtime.sendMessage({ msgName: "whatsMainRating?" }, function(response) {
        var ethicliScore;
        if(response.ethicliStats.overallScore>0){
            ethicliScore = (response.ethicliStats.overallScore).toFixed(1)
        }else{
            ethicliScore = (response.ethicliStats.bcorpScore / 20).toFixed(1)
            if (response.ethicliStats.bcorpCertified && response.ethicliStats.bluesignPartner) {
                // bluesign partners get an extra point added to their score
                ethicliScore = (response.ethicliStats.bcorpScore / 20 + 1).toFixed(1);
            } else if (!response.ethicliStats.bcorpCertified && response.ethicliStats.bluesignPartner) {
                ethicliScore = 7.5;
            }
        }

        //Changes subratings
        document.getElementById("envScore").innerHTML = response.ethicliStats.environmentScore;
        document.getElementById("laborScore").innerHTML = response.ethicliStats.laborScore;
        document.getElementById("animalScore").innerHTML = response.ethicliStats.animalsScore;

        //Changes subratings scorebar
        var envScore = response.ethicliStats.environmentScore*20
        document.getElementById("envScoreBar").style.width = envScore + "px";
        var laborScore = response.ethicliStats.laborScore*20
        document.getElementById("laborScoreBar").style.width = laborScore + "px";
        var animalScore = response.ethicliStats.animalsScore*20
        document.getElementById("animalScoreBar").style.width = animalScore + "px";


        if(document.getElementById("overallScore")!== null){ //checks to see if ID even appears on page
            document.getElementById("overallScore").innerHTML = ethicliScore;
        }

        var badgeCounter = 0;
        if (response.ethicliStats.bcorpCertified) {
            document.getElementById("bcorp").classList.add("trueForPage");
            badgeCounter++;
        }
        if (response.ethicliStats.bluesignPartner) {
            document.getElementById("bluesign").classList.add("trueForPage");
            badgeCounter++;
        }
        if (response.ethicliStats.blackOwnedBusiness) {
            document.getElementById("blackowned").classList.add("trueForPage");
            badgeCounter++;
        }
        if (response.ethicliStats.supportsBLM) {
            document.getElementById("blmsupport").classList.add("trueForPage");
            badgeCounter++;
        }
        if(badgeCounter>0){
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

    document.getElementById("scores").onmouseover = function() {
        document.getElementById("subscoreTip").style.left = (event.clientX-30)+"px";
        document.getElementById("subscoreTip").style.top = (event.clientY-30)+"px";
    };

    document.getElementById("detailsButton").addEventListener("click", function() {
        this.innerHTML = "Details unavailable";
    });
}

function somethingWrong() {
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

        //Pulls and sets email
        document.getElementById("sendEmail").href = sendEmail();
        function sendEmail() {
            var emailUrl = "mailto:team.ethicli@gmail.com?subject=Error%20With%20Current%20Website%20&body=Error%20with%20the%20following%20page:%20"+currentTab.url+"%0d%0aPlease%20let%20us%20know%20what%20is%20wrong%20below.";
            chrome.tabs.create({ url: emailUrl }, function(tab) {
                setTimeout(function() {
                    chrome.tabs.remove(tab.id);
                }, 500);
            });
        }
    });
}
