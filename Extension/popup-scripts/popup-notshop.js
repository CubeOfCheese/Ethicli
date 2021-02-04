import { sendFeedback } from "../popup.js";

window.addEventListener("load", () => {
  document.getElementById("submitLazyFeedback").onclick = () => {
    sendFeedback("ShouldBeShop");
    // Requested Shop Here
  };
});

// Opened-NotShop analytics event
