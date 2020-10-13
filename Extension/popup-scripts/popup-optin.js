window.addEventListener("load", function() {
    document.getElementById("optinYes").addEventListener(
      "click",
      function() {
        chrome.storage.local.set({ "optIn" : true }, function() {
          var query = { active: true, currentWindow: true };
          chrome.tabs.query(query, function callback(tabs) {
            var currentTab = tabs[0];
            chrome.tabs.sendMessage(currentTab.id, { msgName: "reevaluatePage" }, function(response) {
              window.close()
            })
          })
        });
    });

    document.getElementById("optinNo").addEventListener(
      "click",
      function() {
        chrome.storage.local.set({ "optIn" : false }, function() {
          window.close()
        });
    });
});