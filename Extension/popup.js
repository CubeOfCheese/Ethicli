let hasSubscore;
let numSubscores = 0;
let newHeight = 360;
let fullheight = 360;

chrome.runtime.sendMessage({ msgName: "isShoppingPage?" }, (response) => {
  if (response.shoppingPage) {
    chrome.runtime.sendMessage({ msgName: "whatsMainRating?" }, (ratingResponse) => {
      loadExtension(ratingResponse.ethicliStats);
      chrome.runtime.sendMessage({ msgName: "productIdentified?" }, (productResponse) => {
        if (productResponse || ratingResponse.ethicliStats.overallScore > 0) {
          loadSponsor(productResponse.productName, ratingResponse.ethicliStats.overallScore);
        }
      });
    });
  }
});

function loadExtension(ethicliStats) {
  const ethicliScore = (ethicliStats.overallScore).toFixed(1);
  // Badges ------------------------------------------------------------------------------------------------
  let badgeCounter = 0;
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
  if (ethicliStats.veganDotOrgCertified) {
    document.getElementById("vegan").classList.add("trueForPage");
    badgeCounter++;
  }
  if (ethicliStats.leapingBunnyCertified) {
    document.getElementById("leapingbunny").classList.add("trueForPage");
    badgeCounter++;
  }
  if (badgeCounter <= 3) {
    document.getElementById("badges").classList.add("lessThanThreeBadges");
    if (document.getElementById("badgeDisplayer") !== null) {
      document.getElementById("badgeDisplayer").style.display = "none";
    }
  }
  if (badgeCounter > 0) {
    if (document.getElementById("numBadges") !== null) {
      document.getElementById("numBadges").textContent = badgeCounter;
    }
    if (document.getElementById("noBadge") !== null || document.getElementById("hasBadge") !== null) {
      document.getElementById("noBadge").style.display = "none";
      document.getElementById("hasBadge").style.display = "block";
    }
    document.body.style = "height:190px;";
  }

  if (document.getElementById("popupNoRating") != null) { // for no ratings
    document.body.style = "height: 290px;";
    if (badgeCounter > 0) {
      document.body.style = "height: 420px;";
    }
  }
  // End Badges ------------------------------------------------------------------------------------

  if (ethicliScore > 0.0) { // This will need to be updated once negative scores are used as default
    adjustSubscores();
  }

  function adjustSubscores() {
    if (ethicliStats.environmentScore === 0.0) {
      document.getElementById("envSection").style = "display:none;";
      numSubscores++;
    }
    if (ethicliStats.laborScore === 0.0) {
      document.getElementById("laborSection").style = "display:none;";
      numSubscores++;
    }
    if (ethicliStats.animalsScore === 0.0) {
      document.getElementById("animalSection").style = "display:none;";
      numSubscores++;
    }
    if (ethicliStats.socialScore === 0.0) {
      document.getElementById("socialSection").style = "display:none;";
      numSubscores++;
    }
    fullheight = fullheight - numSubscores * 42;

    if (ethicliStats.environmentScore === 0.0
            && ethicliStats.laborScore === 0.0
            && ethicliStats.animalsScore === 0.0
            && ethicliStats.socialScore === 0.0
    ) {
      hasSubscore = false;
      document.getElementById("noSubscore").style = "display:block;";
      document.getElementById("detailsButton").style = "display:none;";
      fullheight = 160;
    } else {
      hasSubscore = true;
    }
    // Changes subratings
    document.getElementById("envScore").textContent = ethicliStats.environmentScore.toFixed(1);
    document.getElementById("laborScore").textContent = ethicliStats.laborScore.toFixed(1);
    document.getElementById("animalScore").textContent = ethicliStats.animalsScore.toFixed(1);
    document.getElementById("socialScore").textContent = ethicliStats.socialScore.toFixed(1);

    // Changes subratings scorebar
    const envScore = ethicliStats.environmentScore * 20;
    document.getElementById("envScoreBar").style.width = envScore + "px";
    const laborScore = ethicliStats.laborScore * 20;
    document.getElementById("laborScoreBar").style.width = laborScore + "px";
    const animalScore = ethicliStats.animalsScore * 20;
    document.getElementById("animalScoreBar").style.width = animalScore + "px";
    const socialScore = ethicliStats.socialScore * 20;
    document.getElementById("socialScoreBar").style.width = socialScore + "px";

    newHeight = "height:" + fullheight + "px;";
    document.body.style = newHeight;
  }

  // Change sitename
  document.getElementById("siteurl").textContent = ethicliStats.name;
  if (ethicliStats.name === null || ethicliStats.name === "") {
    document.getElementById("siteurl").textContent = "Unavailable";
  }

  // Change "View Details" button routing
  const query = { active: true, currentWindow: true };
  chrome.tabs.query(query, (tabs) => {
    const currentTab = tabs[0];
    const companyName = urlToCompanyName(currentTab.url);

    const infoLink = document.createElement("a");
    infoLink.href = "https://ethicli.com/info/" + companyName;
    infoLink.target = "_blank";
    infoLink.textContent = "View Details";
    if (document.getElementById("detailsButton") !== null) {
      document.getElementById("detailsButton").append(infoLink);
      document.getElementById("detailsButton").addEventListener("click", () => {
        reportGA("ViewDetailsClicked");
      });
    }
  });

  if (document.getElementById("overallScore") !== null) { // checks to see if ID even appears on page
    document.getElementById("overallScore").textContent = ethicliScore;
  }
}

function loadSponsor(productName, ethicliScore) {
  const authString = "me:itme";

  // const authString = "<username>:<password>";
  const data = {
    "productName": productName
  };
  fetch("http://localhost:8080/Advertisement/getByProductTags", {
    "method": "PUT",
    "headers": {
      "Content-Type": "application/json",
      "Authorization": "Basic " + btoa(authString),
    },
    "body": JSON.stringify(data)
  }).then((response) => response.json()).then((adToDisplay) => {
    if (adToDisplay.productURL) {
      if (document.getElementById("popupNoRating") !== null) {
        document.body.style = "height:290px;";
      } else {
        fullheight = 540 - numSubscores * 46;
        newHeight = "height:" + fullheight + "px;";
        document.body.style = newHeight;
      }
      document.getElementById("sponsor").style = "display:block;";
      document.getElementById("sponsorLink").href = adToDisplay.productURL;
      document.getElementById("sponsorProductName").textContent = adToDisplay.productName;
      document.getElementById("sponsorCompany").textContent = adToDisplay.companyName;
      document.getElementById("sponsorRating").textContent = adToDisplay.companyScore;
      document.getElementById("sponsorPrice").textContent = adToDisplay.price;
      document.getElementById("sponsorImg").src = adToDisplay.productImageURL;
      reportGA("AdDisplayed");
      document.getElementById("sponsorLink").addEventListener("click", () => {
        reportGA("AdClicked");
      });
    } else {
      document.getElementById("sponsor").style = "display:none;";
    }
  });
}

window.onload = () => {
  document.getElementById("menu").addEventListener("click", () => {
    document.getElementById("menuPanel").classList.toggle("menuClicked");
  });

  document.getElementById("menuBacking").addEventListener("click", () => {
    document.getElementById("menuPanel").classList.remove("menuClicked");
  });

  document.getElementById("somethingWrong").addEventListener("click", () => {
    somethingWrong();
    reportGA("SomethingWrong");
  });
  if (document.getElementById("badgeDisplayer") !== null) {
    document.getElementById("badgeDisplayer").addEventListener("click", () => {
      document.getElementById("popupMain").classList.toggle("badgesExpanded");
      if (document.getElementById("popupMain").classList.contains("badgesExpanded")) {
        document.getElementById("badgeDisplayerTooltip").textContent = "Click to return to score breakdowns";
        document.getElementById("badgeIcon").src = "images/badge-dark.svg";
      } else {
        document.getElementById("badgeDisplayerTooltip").textContent = "Click to view expanded badges";
        document.getElementById("badgeIcon").src = "images/badge.svg";
      }
    });
  }

  if (document.getElementById("scores") != null) { // if there is a scores ID present
    document.getElementById("scores").onmouseover = () => {
      if (hasSubscore) {
        document.getElementById("subscoreTip").style.left = (event.clientX - 30) + "px";
        document.getElementById("subscoreTip").style.top = (event.clientY - 30) + "px";
      } else {
        document.getElementById("subscoreTip").style = "display:none;";
      }
    };
  }

  if (document.getElementById("sitename") != null) {
    fadeLongURL();
  }

  // --- Tutorial --------------------------------------------------------------------------
  let startTutorial = false;
  let currentTutorial;

  document.getElementById("watchTutorial").addEventListener("click", () => {
    document.body.style = "height: 600px;";
    startTutorial = !startTutorial;
    currentTutorial = 1;
    if (startTutorial) {
      document.getElementById("tutorial").style = "display: block";
      document.getElementById("tutorialNavigation").style = "display:flex";
      document.getElementById("tutorial" + currentTutorial).style = "display:block";
    } else {
      document.getElementById("tutorialScreens").style = "display:none";
      document.getElementById("tutorialNavigation").style = "display:none";
    }
    reportGA("TutorialViewed");
  });

  let idname = "tutorial1";
  document.getElementById("tutorialBack").addEventListener("click", () => {
    currentTutorial -= 1;
    tutorialSlideshow();
  });
  document.getElementById("tutorialNext").addEventListener("click", () => {
    currentTutorial += 1;
    tutorialSlideshow();
  });

  function tutorialSlideshow() {
    idname = "tutorial" + (currentTutorial);
    for (let i = 0; i < 7; i++) {
      const tutorialCycle = "tutorial" + (i + 1);
      document.getElementById(tutorialCycle).style = "display:none";
    }
    if (currentTutorial < 1 || currentTutorial > 7) { // Resets tutorial
      startTutorial = false;
      currentTutorial = 1;
      document.getElementById("tutorial").style = "display:none";
      document.getElementById("tutorialNavigation").style = "display:none";
      if (document.getElementById("popupMain") != null) { // for shop pages
        document.body.style = "height:" + fullheight + "px;";
      } else if (document.getElementById("popupNoRating") != null) { // for no ratings
        document.body.style = "height: 290px;";
      } else if (document.getElementById("popupNotShop") != null) { // for non-shops
        document.body.style = "height: 136px;";
      } else {
        document.body.style = "height: " + newHeight + "px;";
      }
    } else {
      document.getElementById(idname).style = "display:block";
    }

    if (currentTutorial === 6 || currentTutorial === 7) {
      document.getElementById("tutorialNavigation").style = "display:flex; bottom:476px;";
    } else {
      document.getElementById("tutorialNavigation").style = "display:flex; bottom:8px;";
    }
  }

  document.getElementById("visitWebsite").addEventListener("click", () => {
    reportGA("VisitedWebsite");
  });
};

function fadeLongURL() {
  document.getElementById("siteurl").addEventListener("mouseover", () => {
    const siteurlLength = this.innerHTML.length + 16;
    if (siteurlLength > 40) {
      this.style = "margin-left: -" + (siteurlLength) + "px;";
      document.getElementById("siteurlcontainer").style
                = `-webkit-mask-image: linear-gradient(to right, transparent 0%, black 5%, black 100%, transparent 100%);
                mask-image: linear-gradient(to right, transparent 0%, black 5%, black 100%, transparent 100%)`;
    }
  });
  document.getElementById("siteurl").addEventListener("mouseout", () => {
    this.style = "margin-left: 0px;";
    document.getElementById("siteurlcontainer").style
            = `-webkit-mask-image: linear-gradient(to right, black 90%, transparent 100%);
            mask-image: linear-gradient(to right, black 90%, transparent 100%)`;
  });
}

function urlToCompanyName(url) {
  if (url.substring(0, 8) === "https://") {
    url = url.substring(8);
  } else if (url.substring(0, 7) === "http://") {
    url = url.substring(7);
  }
  let endOfBaseDomain = url.search(/\//);
  if (endOfBaseDomain > -1) {
    url = url.substring(0, endOfBaseDomain);
  }
  const endOfSubDomain = url.lastIndexOf(".", url.lastIndexOf(".") - 1);
  url = url.substring(endOfSubDomain + 1);
  endOfBaseDomain = url.search(/\./);
  if (endOfBaseDomain > -1) {
    url = url.substring(0, endOfBaseDomain);
  }
  return url;
}

function somethingWrong() {
  const query = { active: true, currentWindow: true };
  chrome.tabs.query(query, (tabs) => {
    const currentTab = tabs[0];
    const fetchUrlFeedback = "http://localhost:8080/feedback";
    const fetchData = {
      url: currentTab.url
    };
    // const authString = "<username>:<password>";
    const authString = "me:itme";
    const fetchParams = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Basic " + btoa(authString)
      },
      body: JSON.stringify(fetchData)
    };
    fetch(fetchUrlFeedback, fetchParams);

    // Pulls and sets email
    document.getElementById("sendEmail").href = sendEmail();

    function sendEmail() {
      const emailUrl = "mailto:hello@ethicli.com?subject=Error%20With%20Current%20Website%20&body=Error%20with%20the%20following%20page:%20"
        + currentTab.url + "%0d%0aPlease%20let%20us%20know%20what%20is%20wrong%20below.";
      chrome.tabs.create({ url: emailUrl }, (tab) => {
        setTimeout(() => {
          chrome.tabs.remove(tab.id);
        }, 500);
      });
    }
  });
}


// GOOGLE ANALYTICS
const GA_TRACKING_ID = "UA-173025073-1";
const GA_CLIENT_ID = "4FB5D5BF-B582-41AD-9BDF-1EC789AE6544";

function reportGA(aType) {
  try {
    const request = new XMLHttpRequest();
    const message
      = "v=1&tid=" + GA_TRACKING_ID + "&cid= " + GA_CLIENT_ID + "&aip=1"
      + "&ds=add-on&t=event&ec=VISITORS&ea=" + aType;
    request.open("POST", "https://www.google-analytics.com/collect", true);
    request.send(message);
  } catch (e) {
    console.error("Error sending report to Google Analytics.\n" + e);
  }
}
