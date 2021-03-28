const config = {
  "username": "",
  "password": "",
  "mixpanel_code": "",
  "mixpanel_config": {
    "api_host": "https://api.mixpanel.com",
    "ip": false,
    "property_blacklist": [
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
};

export default config;
