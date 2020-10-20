// for popupNoRating.html

document.addEventListener("DOMContentLoaded", windowOnload, false);

function windowOnload() {
  document.getElementById("requestrating").addEventListener("click", () => {
    requestShop();
  });
}

function requestShop() { // runs when user hits "Request this Shop" button
  let userEmailHTML = "";
  userEmailHTML = document.getElementById("emailrequest").value;
  const query = { active: true, currentWindow: true };
  chrome.tabs.query(query, function callback(tabs) {
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
          console.log("aw nards: " + error.message); // not necessary
          reportGA("RatingRequestFailed - " + error.message);
        });
  });
  reportGA("ShopRequested");
}

// GOOGLE ANALYTICS
const GA_TRACKING_ID = "UA-173025073-1";
const GA_CLIENT_ID = "4FB5D5BF-B582-41AD-9BDF-1EC789AE6544";

function reportGA(aType) {
  try {
    const request = new XMLHttpRequest();
    const message =
      "v=1&tid=" + GA_TRACKING_ID + "&cid= " + GA_CLIENT_ID + "&aip=1" +
      "&ds=add-on&t=event&ec=VISITORS&ea=" + aType;
    request.open("POST", "https://www.google-analytics.com/collect", true);
    request.send(message);
  } catch (e) {
    console.error("Error sending report to Google Analytics.\n" + e);
  }
}

reportGA("Opened-SomethingWrong");
