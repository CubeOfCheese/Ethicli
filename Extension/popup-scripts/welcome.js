import config from "../config/config.js";
import mixpanel from "mixpanel-browser";
mixpanel.init(config.mixpanel_code, config.mixpanel_config);

window.onload = () => {
  // --- General Styling --------------------------------------------------------------------------
  document.getElementById("dropdownMenu").addEventListener("mouseover", () => {
    document.getElementById("dropdownIcon").classList.toggle("dropped");
    document.getElementById("submenu").classList.toggle("submenuClicked");
  });

  document.getElementById("triplebar").addEventListener("click", () => { // allows menu to toggle when starting at a mobile size
    document.getElementById("navlinks").classList.toggle("navClicked"); // create mobile menu toggle to show navigation links
  });

  window.onresize = () => {
    if (window.innerWidth > 768) {
      document.getElementById("navlinks").classList.remove("navClicked");
      document.getElementById("submenu").classList.remove("submenuClicked");
      document.getElementById("dropdownIcon").classList.remove("dropped");
    }
  };

  // --- Optin --------------------------------------------------------------------------
  document.getElementById("optinAccepted").addEventListener("click", () => {
    chrome.storage.local.set({ "optIn": true }, () => {
      const query = { active: true, currentWindow: true };
      chrome.tabs.query(query, (tabs) => {
        const currentTab = tabs[0];
        chrome.tabs.sendMessage(currentTab.id, { msgName: "reevaluatePage" }, () => {
          document.getElementById("optinAcceptedResult").style = "display:block;";
          document.getElementById("optinDeclinedResult").style = "display:none;";
          // confetti.start(1000, 150);
        });
      });
    });
  });

  document.getElementById("optinDeclined").addEventListener("click", () => {
    chrome.management.uninstallSelf(() => {
      document.getElementById("optinAcceptedResult").style = "display:none;";
      document.getElementById("optinDeclinedResult").style = "display:block;";
    });
  });

  document.getElementById("analyticsToggle").addEventListener("click", () => {
    if (document.getElementById("analyticsToggle").checked) {
      mixpanel.opt_in_tracking();
    } else {
      mixpanel.opt_out_tracking();
    }
  });


  // --- Tutorial --------------------------------------------------------------------------
  let startTutorial = false;
  let currentTutorial = 0;

  document.getElementById("hideTutorial").addEventListener("click", () => {
    document.getElementById("tutorial").classList.toggle("hideTutorial");
    if (startTutorial) {
      document.getElementById("tutorial").classList.remove("tutorialHeightAdjust");
    } else {
      document.getElementById("tutorial").classList.add("tutorialHeightAdjust");
    }
  });

  document.getElementById("watchTutorial").addEventListener("click", () => {
    startTutorial = !startTutorial;
    currentTutorial = 0;
    if (startTutorial) {
      document.getElementById("tutorialScreens").style = "display:block;";
      document.getElementById("tutorialNavigation").style = "display:flex;";
      document.querySelectorAll(".tscreen")[currentTutorial].style = "display:block;";
      document.getElementById("tutorial").style = "height:624px;";
    } else {
      document.getElementById("tutorialScreens").style = "display:none;";
      document.getElementById("tutorialNavigation").style = "display:none;";
    }
  });

  document.getElementById("tutorialBack").addEventListener("click", () => {
    currentTutorial -= 1; tutorialSlideshow();
  });

  document.getElementById("tutorialNext").addEventListener("click", () => {
    currentTutorial += 1; tutorialSlideshow();
  });

  function tutorialSlideshow() {
    for (let i = 0; i < 7; i++) {
      document.querySelectorAll(".tscreen")[i].style = "display:none;";
    }

    if (currentTutorial === 5 || currentTutorial === 6) {
      document.getElementById("tutorialNavigation").style = "display:flex;bottom:476px;";
    } else {
      document.getElementById("tutorialNavigation").style = "display:flex;bottom:8px;";
    }

    if (currentTutorial < 0 || currentTutorial > 6) { // Resets tutorial
      startTutorial = false;
      currentTutorial = 0;
      document.getElementById("tutorialScreens").style = "display:none;";
      document.getElementById("tutorialNavigation").style = "display:none;";
      document.getElementById("tutorial").style = "height:400px;";
    } else {
      document.querySelectorAll(".tscreen")[currentTutorial].style = "display:block;";
    }
  }

  document.getElementById("confettiLogo").addEventListener("click", () => {
    // confetti.start(1000, 150);
  });

  document.getElementById("currentYear").innerText = new Date().getFullYear(); // updates copyright with current year
};
