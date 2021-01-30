// for popupNoRating.html
const HEIGHT_NORATING_BADGES = 136;
const HEIGHT_NORATING_REQUEST = 283;

document.addEventListener("DOMContentLoaded", windowOnload, false);

function windowOnload() {
  document.getElementById("requestrating").addEventListener("click", () => {
    requestShop();
  });
}

chrome.runtime.sendMessage({ msgName: "whatsMainRating?" }, (ratingResponse) => {
  loadExtension(ratingResponse.ethicliStats);
});

function loadExtension(ethicliStats) {
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
  }
  if (badgeCounter > 0) {
    document.getElementById("noBadge").style.display = "none";
    document.getElementById("hasBadge").style.display = "block";
  }

  // --- Setting Height --------------------------------------------------------------------------
  let fullheight = HEIGHT_NORATING_REQUEST;
  if (badgeCounter > 0) {
    fullheight += HEIGHT_NORATING_BADGES;
  }
  document.body.style = "height:" + fullheight + "px;";
}

function requestShop() { // runs when user hits "Request this Shop" button
  let userEmailHTML = "";
  userEmailHTML = document.getElementById("emailrequest").value;
  const query = { active: true, currentWindow: true };
  chrome.tabs.query(query, (tabs) => {
    const currentTab = tabs[0];
    const fetchUrlFeedback = "https://ethicli.com/feedback";
    const fetchData = {
      url: currentTab.url,
      userEmail: userEmailHTML,
      messageType: "RequestShop"
    };
    const fetchParams = {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(fetchData)
    };
    fetch(fetchUrlFeedback, fetchParams)
        .then((response) => {
          if (response.status !== 200) {
            throw new Error("404");
          }
          document.getElementById("ratingPreRequest").classList.add("requestsubmitted");
          document.getElementById("ratingPostRequest").classList.add("requestsubmitted");
          const emailstr = String(userEmailHTML).replace(/\s+/g, "");
          if (emailstr !== "") {
            document.getElementById("uemail").innerText = userEmailHTML;
          } else {
            document.getElementById("withemail").style = "display:none;";
          }
          confetti.start(1000, 60);
        })
        .catch((error) => {
          document.getElementById("ratingPreRequest").classList.add("failed");
          document.getElementById("messageFailed").classList.add("failed");
          // "RatingRequestFailed - " + error.message analytics event
        });
  });
  // ShopRequested analytics event
}

// Opened-NoRating analytics event
