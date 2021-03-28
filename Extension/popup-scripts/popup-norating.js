import config from "../config/config.js";
import { sendFeedback } from "../popup-scripts/all-popups.js";
import mixpanel from "mixpanel-browser";
mixpanel.init(config.mixpanel_code, config.mixpanel_config);

const HEIGHT_NORATING_BADGES = 136;
const HEIGHT_NORATING_REQUEST = 283;

document.addEventListener("DOMContentLoaded", windowOnload, false);

function windowOnload() {
  document.getElementById("submitLazyFeedback").addEventListener("click", () => {
    const userEmail = document.getElementById("emailrequest").value;
    sendFeedback("RequestShop", userEmail);
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

const query = { active: true, currentWindow: true };
chrome.tabs.query(query, (tabs) => {
  const currentTab = tabs[0];
  mixpanel.track("Open popup", {
    "Is Shop": true,
    "Has Score": false,
    "Shop URL": currentTab.url
  });
});
