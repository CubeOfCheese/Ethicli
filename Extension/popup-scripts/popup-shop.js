// for popup.html
import mixpanel from "mixpanel-browser";
mixpanel.init("db3fa3fa397bb591b339887d12b1c13e", { api_host: "https://api.mixpanel.com" });

chrome.runtime.sendMessage({ msgName: "isShoppingPage?" }, (response) => {
  if (response.shoppingPage) {
    chrome.runtime.sendMessage({ msgName: "whatsMainRating?" }, (ratingResponse) => {
      const roundedOverall = ratingResponse.ethicliStats.overallScore.toFixed(1);
      updateOverallToolTip(roundedOverall);
    });
  }
});

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

// --- Extension Tooltip --------------------------------------------------------------------------
window.addEventListener("load", () => {
  document.getElementById("overallScoreGroup").addEventListener("mouseenter", () => {
    document.getElementById("overallScoreGroup").classList.add("showtip");
  });
  document.getElementById("overallScoreGroup").addEventListener("mouseleave", () => {
    document.getElementById("overallScoreGroup").classList.remove("showtip");
  });
});

mixpanel.track("Opened-ShopHasRating");
