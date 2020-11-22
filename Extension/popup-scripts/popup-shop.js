// for popup.html

chrome.runtime.sendMessage({ msgName: "isShoppingPage?" }, (response) => {
  if (response.shoppingPage) {
    chrome.runtime.sendMessage({ msgName: "whatsMainRating?" }, (ratingResponse) => {
      let overalltip;
      const OVERALLSCORE = ratingResponse.ethicliStats.overallScore;
      if (OVERALLSCORE < 4) {
        overalltip = "Could be better!";
        document.getElementById("overallScoreTooltip").style.background = "#D3A792";
        document.getElementById("overallScoreTooltip").style.color = "#fff";
      } else if (OVERALLSCORE >= 4 && OVERALLSCORE < 7) {
        overalltip = "Average-scoring brand";
        document.getElementById("overallScoreTooltip").style.background = "#CDBEAC";
        document.getElementById("overallScoreTooltip").style.color = "#fff";
      } else if (OVERALLSCORE >= 7 && OVERALLSCORE < 8) {
        overalltip = "A good rating!";
        document.getElementById("overallScoreTooltip").style.background = "#CBC7B6";
      } else if (OVERALLSCORE >= 8 && OVERALLSCORE < 9) {
        overalltip = "A great rating!";
        document.getElementById("overallScoreTooltip").style.background = "#C8CFBF";
      } else if (OVERALLSCORE >= 9 && OVERALLSCORE < 10) {
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
    });
  }
});

// --- Extension Tooltip --------------------------------------------------------------------------
window.addEventListener("load", () => {
  document.getElementById("overallScoreGroup").addEventListener("mouseenter", () => {
    document.getElementById("overallScoreGroup").classList.add("showtip");
  });
  document.getElementById("overallScoreGroup").addEventListener("mouseleave", () => {
    document.getElementById("overallScoreGroup").classList.remove("showtip");
  });
});

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

reportGA("Opened-ShopHasRating");
