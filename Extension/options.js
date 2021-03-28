import config from "../config/config.js";
import mixpanel from "mixpanel-browser";
mixpanel.init(config.mixpanel_code, config.mixpanel_config);

window.onload = () => {
  document.getElementById("analyticsToggle").checked = !mixpanel.has_opted_out_tracking();
  document.getElementById("analyticsToggle").addEventListener("click", () => {
    if (document.getElementById("analyticsToggle").checked) {
      mixpanel.opt_in_tracking();
    } else {
      mixpanel.opt_out_tracking();
    }
  });
};
