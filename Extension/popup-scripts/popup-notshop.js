// for popupNotShop.html

// GOOGLE ANALYTICS
const GA_TRACKING_ID = "UA-173025073-1";
const GA_CLIENT_ID = "4FB5D5BF-B582-41AD-9BDF-1EC789AE6544";

function reportGA(aType) {
  try {
    const url = "https://www.google-analytics.com/collect";
    const message = `v=1&tid=${GA_TRACKING_ID}&cid=${GA_CLIENT_ID}&aip=1&ds=add-on&t=event&ec=VISITORS&ea=${aType}`;
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: message
    });
  } catch (e) {
    console.error("Error sending report to Google Analytics.\n" + e);
  }
}

reportGA("Opened-NotShop");
