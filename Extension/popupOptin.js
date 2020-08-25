window.addEventListener("load", function() {
    document.getElementById("optinYes").addEventListener(
      "click",
      function() {
        chrome.storage.local.set({ "optIn" : true }, function() {
          window.close()
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
