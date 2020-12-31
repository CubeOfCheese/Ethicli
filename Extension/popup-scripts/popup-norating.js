// for popupNoRating.html
import mixpanel from "mixpanel-browser";
mixpanel.init("db3fa3fa397bb591b339887d12b1c13e", { api_host: "https://api.mixpanel.com" });

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
          mixpanel.track("RatingRequestFailed - " + error.message);
        });
  });
  mixpanel.track("ShopRequested");
}
mixpanel.track("Opened-NoRating");
