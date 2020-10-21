window.addEventListener("load", () => {
  document.getElementById("optinYes").addEventListener("click", () => {
    chrome.storage.local.set({ "optIn": true }, () => {
      const query = { active: true, currentWindow: true };
      chrome.tabs.query(query, (tabs) => {
        const currentTab = tabs[0];
        chrome.tabs.sendMessage(currentTab.id, { msgName: "reevaluatePage" }, function(response) {
          window.close();
        });
      });
    });
  });

  document.getElementById("optinNo").addEventListener("click", () => {
    chrome.storage.local.set({ "optIn": false }, () => {
      window.close();
    });
  });
});
