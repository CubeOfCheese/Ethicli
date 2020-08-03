var hasSubscore;

chrome.runtime.sendMessage({ msgName: "isShoppingPage?" }, function(response) {
    if (response.shoppingPage) {
      chrome.runtime.sendMessage({ msgName: "whatsMainRating?" }, function(response) {
        loadExtension(response.ethicliStats);
      });
    }
});

function loadExtension(ethicliStats) {
    var ethicliScore = (ethicliStats.overallScore).toFixed(1);
    if (ethicliScore > 0.0) {  // This will need to be updated once negative scores are used as default
      adjustSubscores();
    }
    function adjustSubscores() {
        var fullheight = 340;
        if (ethicliStats.environmentScore == 0.0) {
            fullheight= fullheight-50;
            document.getElementById("envSection").style="display:none;";
        }
        if (ethicliStats.laborScore == 0.0) {
            fullheight= fullheight-50;
            document.getElementById("laborSection").style="display:none;";
        }
        if (ethicliStats.animalsScore == 0.0) {
            fullheight= fullheight-50;
            document.getElementById("animalSection").style="display:none;";
        }
        if (ethicliStats.environmentScore == 0.0 &&
            ethicliStats.laborScore == 0.0 &&
            ethicliStats.animalsScore == 0.0
        ){
            hasSubscore = false;
            document.getElementById("noSubscore").style="display:block;";
            document.getElementById("detailsButton").style="display:none;"
            fullheight = 160;
        } else {
            hasSubscore = true;
        }
        var newHeight = "height:"+fullheight+"px;";
        document.body.style = newHeight;
    }

    //Change sitename
    document.getElementById("siteurl").innerHTML = ethicliStats.name;
    if (ethicliStats.name == null) {
        document.getElementById("siteurl").innerHTML = "Unavailable";
    }

    //Change "View Details" button routing
    var query = { active: true, currentWindow: true };
    chrome.tabs.query(query, function callback(tabs) {
      var currentTab = tabs[0];
      document.getElementById("detailsButton").innerHTML =
      "<a href='https://ethicli.com/info/"+ currentTab.url +"' target='_blank'>View Details</a?";
    })

    //Changes subratings
    document.getElementById("envScore").innerHTML = ethicliStats.environmentScore.toFixed(1);
    document.getElementById("laborScore").innerHTML = ethicliStats.laborScore.toFixed(1);
    document.getElementById("animalScore").innerHTML = ethicliStats.animalsScore.toFixed(1);

    //Changes subratings scorebar
    var envScore = ethicliStats.environmentScore*20;
    document.getElementById("envScoreBar").style.width = envScore + "px";
    var laborScore = ethicliStats.laborScore*20;
    document.getElementById("laborScoreBar").style.width = laborScore + "px";
    var animalScore = ethicliStats.animalsScore*20;
    document.getElementById("animalScoreBar").style.width = animalScore + "px";

    if (document.getElementById("overallScore")!== null) { //checks to see if ID even appears on page
        document.getElementById("overallScore").innerHTML = ethicliScore;
    }

    var badgeCounter = 0;
    if (ethicliStats.bcorpCertified) {
        document.getElementById("bcorp").classList.add("trueForPage");
        badgeCounter++;
    }
    if (ethicliStats.bluesignPartner) {
        document.getElementById("bluesign").classList.add("trueForPage");
        badgeCounter++;
    }
    if (ethicliStats.blackOwnedBusiness) {
        document.getElementById("blackowned").classList.add("trueForPage");
        badgeCounter++;
    }
    if (ethicliStats.supportsBLM) {
        document.getElementById("blmsupport").classList.add("trueForPage");
        badgeCounter++;
    }
    if (badgeCounter>0) {
        document.getElementById("noBadge").style.display = "none";
        document.getElementById("hasBadge").style.display = "block";
        document.body.style = "height:190px;"
    }
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
        if (hasSubscore) {
            document.getElementById("subscoreTip").style.left = (event.clientX-30)+"px";
            document.getElementById("subscoreTip").style.top = (event.clientY-30)+"px";
        } else {
            document.getElementById("subscoreTip").style = "display:none;";
        }
    };
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
            var emailUrl = "mailto:hello@ethicli.com?subject=Error%20With%20Current%20Website%20&body=Error%20with%20the%20following%20page:%20"+currentTab.url+"%0d%0aPlease%20let%20us%20know%20what%20is%20wrong%20below.";
            chrome.tabs.create({ url: emailUrl }, function(tab) {
                setTimeout(function() {
                    chrome.tabs.remove(tab.id);
                }, 500);
            });
        }
    });
}
