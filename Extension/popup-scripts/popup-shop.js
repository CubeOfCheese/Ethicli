import config from "../config/config.js";
import { getDomainWithoutSuffix } from "tldts-experimental";
import { sendFeedback } from "../popup-scripts/all-popups.js";
import mixpanel from "mixpanel-browser";
mixpanel.init(config.mixpanel_code, config.mixpanel_config);

const HEIGHT_POPUP_OVERALLSCORE = 160;
const HEIGHT_POPUP_VIEWDETAILS = 36 + 42;
const HEIGHT_POPUP_SUBSCORE = 42;
const HEIGHT_POPUP_SPONSOR = 174;

window.addEventListener("load", () => {
  // --- Extension Tooltip --------------------------------------------------------------------------
  document.getElementById("overallScoreGroup").addEventListener("mouseenter", () => {
    document.getElementById("overallScoreGroup").classList.add("showtip");
  });
  document.getElementById("overallScoreGroup").addEventListener("mouseleave", () => {
    document.getElementById("overallScoreGroup").classList.remove("showtip");
  });

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

  // --- Request Recommended Product ------------------------------------------------------------------
  document.getElementById("submitLazyFeedback").onclick = () => {
    sendFeedback("ProductRequest");
  };
});

chrome.runtime.sendMessage({ msgName: "whatsMainRating?" }, (ratingResponse) => {
  loadExtension(ratingResponse.ethicliStats);
  const roundedOverall = ratingResponse.ethicliStats.overallScore.toFixed(1);
  updateOverallToolTip(roundedOverall);
  chrome.runtime.sendMessage({ msgName: "productIdentified?" }, (productResponse) => {
    if (productResponse || ratingResponse.ethicliStats.overallScore > 0) {
      loadSponsor(productResponse.productName, ratingResponse.ethicliStats.overallScore);
    }
  });
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
    document.getElementById("badgeDisplayer").style.display = "none";
  }
  if (badgeCounter > 0) {
    document.getElementById("numBadges").textContent = badgeCounter;
  }

  // End Badges ------------------------------------------------------------------------------------

  if (ethicliScore > 0.0) { // This will need to be updated once negative scores are used as default
    adjustSubscores();
  }

  function adjustSubscores() {
    let numSubscores = 0;
    if (ethicliStats.environmentScore !== 0.0) {
      document.getElementById("envSection").style = "display:block;";
      numSubscores++;
    }
    if (ethicliStats.laborScore !== 0.0) {
      document.getElementById("laborSection").style = "display:block;";
      numSubscores++;
    }
    if (ethicliStats.animalsScore !== 0.0) {
      document.getElementById("animalSection").style = "display:block;";
      numSubscores++;
    }
    if (ethicliStats.socialScore !== 0.0) {
      document.getElementById("socialSection").style = "display:block;";
      numSubscores++;
    }

    const SUBSCORE_SECTION_HEIGHT = HEIGHT_POPUP_SUBSCORE * numSubscores;

    if (ethicliStats.environmentScore === 0.0
            && ethicliStats.laborScore === 0.0
            && ethicliStats.animalsScore === 0.0
            && ethicliStats.socialScore === 0.0
    ) {
      document.getElementById("subscoreTip").style = "display:none;";
      document.getElementById("noSubscore").style = "display:block;";
    } else {
      document.getElementById("scores").onmouseover = () => {
        document.getElementById("subscoreTip").style.left = (event.clientX - 30) + "px";
        document.getElementById("subscoreTip").style.top = (event.clientY - 30) + "px";
      };
    }

    const fullheight = HEIGHT_POPUP_OVERALLSCORE + SUBSCORE_SECTION_HEIGHT + HEIGHT_POPUP_VIEWDETAILS;
    document.body.style = "height:" + fullheight + "px;";

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
    const companyName = getDomainWithoutSuffix(currentTab.url);

    const infoLink = document.createElement("a");
    infoLink.href = "https://info.ethicli.com/info/" + companyName;
    infoLink.target = "_blank";
    infoLink.textContent = "View Details";
    document.getElementById("detailsButton").append(infoLink);
    document.getElementById("detailsButton").addEventListener("click", () => {
      mixpanel.track("Click view details");
    });
  });

  document.getElementById("overallScore").textContent = ethicliScore;
}

function loadSponsor(productName, ethicliScore) {
  const authString = config.username + ":" + config.password;
  const data = {
    "productName": productName,
    "currentCompanyScore": ethicliScore
  };
  fetch("https://info.ethicli.com/Advertisement/getByProductTags", {
    "method": "PUT",
    "headers": {
      "Content-Type": "application/json",
      "Authorization": "Basic " + btoa(authString),
    },
    "body": JSON.stringify(data)
  }).then((response) => response.json()).then((adToDisplay) => {
    if (adToDisplay.productURL) {
      document.getElementById("sponsor").style = "display:block;";
      document.getElementById("sponsorLink").href = adToDisplay.productURL;
      document.getElementById("sponsorProductName").textContent = adToDisplay.productName;
      document.getElementById("sponsorCompany").textContent = adToDisplay.companyName;
      document.getElementById("sponsorRating").textContent = adToDisplay.companyScore;
      document.getElementById("sponsorPrice").textContent = adToDisplay.price;
      document.getElementById("sponsorImg").src = adToDisplay.productImageURL;
      const fullheight = document.body.style.height.slice(0, -2) + HEIGHT_POPUP_SPONSOR;
      document.body.style = "height:" + fullheight + "px;";
      mixpanel.track("View ad", {
        "Price": adToDisplay.price,
        "Score": adToDisplay.companyScore,
        "Score Differential": adToDisplay.companyScore - ethicliScore,
        "Product Name": adToDisplay.productName,
        "Shop Name": adToDisplay.companyName
      });
      document.getElementById("sponsorLink").addEventListener("click", () => {
        mixpanel.track("Click ad", {
          "Price": adToDisplay.price,
          "Score": adToDisplay.companyScore,
          "Score Differential": adToDisplay.companyScore - ethicliScore,
          "Product Name": adToDisplay.productName,
          "Shop Name": adToDisplay.companyName
        });
      });
      document.getElementById("submitLazyFeedback").style = "display:none";
    } else {
      document.getElementById("sponsor").style = "display:none;";
      document.getElementById("lazyFeedback").style = "display:block;";
    }
  });
}

function updateOverallToolTip(overallscore) {
  let overalltip;
  if (overallscore < 4) {
    overalltip = "Could be better!";
    document.getElementById("overallScoreTooltip").style.background = "#D3A792";
    document.getElementById("overallScoreTooltip").style.color = "#fff";
  } else if (overallscore >= 4 && overallscore < 7) {
    overalltip = "Average-scoring brand";
    document.getElementById("overallScoreTooltip").style.background = "#CDBEAC";
    document.getElementById("overallScoreTooltip").style.color = "#fff";
  } else if (overallscore >= 7 && overallscore < 8) {
    overalltip = "A good rating!";
    document.getElementById("overallScoreTooltip").style.background = "#CBC7B6";
  } else if (overallscore >= 8 && overallscore < 9) {
    overalltip = "A great rating!";
    document.getElementById("overallScoreTooltip").style.background = "#C8CFBF";
  } else if (overallscore >= 9 && overallscore < 10) {
    overalltip = "Fantastic score!";
    document.getElementById("overallScoreTooltip").style.background = "#C6D4C5";
    document.getElementById("overallScoreGroup").addEventListener("mouseenter", () => {
      confetti.start(2000, 30);
    });
  } else {
    overalltip = "Something went wrong";
    document.getElementById("overallScoreTooltip").style.background = "#E07A5F";
  }
  document.getElementById("overallScoreTooltip").innerText = overalltip;
}

const query = { active: true, currentWindow: true };
chrome.tabs.query(query, (tabs) => {
  const currentTab = tabs[0];
  mixpanel.track("Open popup", {
    "Is Shop": true,
    "Has Score": true,
    "Shop URL": currentTab.url
  });
});
