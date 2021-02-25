import { sendFeedback } from "../popup-scripts/all-popups.js";

window.addEventListener("load", () => {
  document.getElementById("submitLazyFeedback").onclick = () => {
    sendFeedback("ShouldBeShop");
    // Requested Shop Here
  };
});

// Opened-NotShop analytics event
