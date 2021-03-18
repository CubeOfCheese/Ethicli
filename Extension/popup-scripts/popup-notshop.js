import { sendFeedback } from "../popup-scripts/all-popups.js";
import mixpanel from "mixpanel-browser";
mixpanel.init("db3fa3fa397bb591b339887d12b1c13e", { api_host: "https://api.mixpanel.com" });

window.addEventListener("load", () => {
  document.getElementById("submitLazyFeedback").onclick = () => {
    sendFeedback("ShouldBeShop");
    // Requested Shop Here
  };
});

mixpanel.track("Opened-NotShop");
