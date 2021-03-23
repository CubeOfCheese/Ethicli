import { sendFeedback } from "../popup-scripts/all-popups.js";
import mixpanel from "mixpanel-browser";
mixpanel.init("db3fa3fa397bb591b339887d12b1c13e",
    { api_host: "https://api.mixpanel.com",
      ip: false,
      property_blacklist: [
        "$city",
        "$region",
        "mp_country_code",
        "$os",
        "$browser_version",
        "$current_url",
        "$initial_referring_domain",
        "$initial_referrer",
        "$referrer",
        "$search_engine"
      ]
    }
);

window.addEventListener("load", () => {
  document.getElementById("submitLazyFeedback").onclick = () => {
    sendFeedback("ShouldBeShop");
  };
});

mixpanel.track(
    "Open popup",
    {
      "Is Shop": false,
      "Has Score": false,
    }
);
