// for popupNotShop.html
import mixpanel from "mixpanel-browser";
mixpanel.init("db3fa3fa397bb591b339887d12b1c13e", { api_host: "https://api.mixpanel.com" });

mixpanel.track("Opened-NotShop");
