import mixpanel from "mixpanel-browser";
mixpanel.init("db3fa3fa397bb591b339887d12b1c13e", { api_host: "https://api.mixpanel.com" });
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
