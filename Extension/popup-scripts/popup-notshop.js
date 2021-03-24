import config from "../config/config.js";
import { sendFeedback } from "../popup-scripts/all-popups.js";
import mixpanel from "mixpanel-browser";
mixpanel.init(config.mixpanel_code, config.mixpanel_config);

window.addEventListener("load", () => {
  document.getElementById("submitLazyFeedback").onclick = () => {
    sendFeedback("ShouldBeShop");
  };
});

mixpanel.track("Open popup", {
  "Is Shop": false,
  "Has Score": false,
});
