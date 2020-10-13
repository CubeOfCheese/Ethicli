// for popupNoRating.html
userEmailHTML = "";
uemail = "";

document.addEventListener('DOMContentLoaded', windowOnload, false);

function windowOnload(){
  document.getElementById("requestrating").addEventListener("click", () => {
    confetti.start(1000, 60);
    document.getElementById("ratingPreRequest").classList.add("requestsubmitted");
    document.getElementById("ratingPostRequest").classList.add("requestsubmitted");
    let emailstr = String(userEmailHTML).replace(/\s+/g, '');
    if(emailstr !== ""){
      document.getElementById("uemail").innerText = userEmailHTML;
    } else {
      document.getElementById("withemail").style = "display:none;";
    }
  })
  
  document.getElementById("emailrequest").addEventListener("blur", () => {
    userEmailHTML = document.getElementById("emailrequest").value;
  })
}

function requestShop() { //runs when user hits "Request this Shop" button
  var query = { active: true, currentWindow: true };
  chrome.tabs.query(query, function callback(tabs) {
      var currentTab = tabs[0];
      var fetchUrlFeedback = "https://ethicli.com/feedback";
      let fetchData = {
          url: currentTab.url,
          userEmail: userEmailHTML,
          messageType: "RequestShop"
      };
      let fetchParams = {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json',
          },
          body: JSON.stringify(fetchData)
      }
      fetch(fetchUrlFeedback, fetchParams)
      .then((response) => {
        document.getElementById("emailrequest").innerText = response;
        //may have to be response.body.someting
        //occurs after button is pressed
      })
  });
  reportGA("ShopRequested");
}

// GOOGLE ANALYTICS
const GA_TRACKING_ID = "UA-173025073-1";
const GA_CLIENT_ID = "4FB5D5BF-B582-41AD-9BDF-1EC789AE6544";

function reportGA(aType) {
  try {
    let request = new XMLHttpRequest();
    let message =
      "v=1&tid=" + GA_TRACKING_ID + "&cid= " + GA_CLIENT_ID + "&aip=1" +
      "&ds=add-on&t=event&ec=VISITORS&ea=" + aType;
    request.open("POST", "https://www.google-analytics.com/collect", true);
    request.send(message);
  } catch (e) {
    console.error("Error sending report to Google Analytics.\n" + e);
  }
}

reportGA("Opened-SomethingWrong");
