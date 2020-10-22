window.onload = () => {
  // --- General Styling --------------------------------------------------------------------------
  dropdown();
  function dropdown() {
    document.getElementById("dropdownMenu").addEventListener("click", () => {
      document.getElementById("dropdownIcon").classList.toggle("dropped");
      document.getElementById("submenu").classList.toggle("submenuClicked");
    });
  }

  mobileNav(); // allows menu to toggle when starting at a mobile size
  function mobileNav() { // create mobile menu toggle to show navigation links
    document.getElementById("triplebar").addEventListener("click", () => {
      document.getElementById("navlinks").classList.toggle("navClicked");
    });
  }

  window.addEventListener("onresize", () => {
    if (window.innerWidth > 768) {
      document.getElementById("navlinks").classList.remove("navClicked");
      document.getElementById("submenu").classList.remove("submenuClicked");
      document.getElementById("dropdownIcon").classList.remove("dropped");
    }
  });

  // --- Optin --------------------------------------------------------------------------
  document.getElementById("optinAccepted").addEventListener("click", () => {
    document.getElementById("optinAcceptedResult").style = "display:block;";
    document.getElementById("optinDeclinedResult").style = "display:none;";
    confetti.start(1000, 150);
  });

  document.getElementById("optinDeclined").addEventListener("click", () => {
    document.getElementById("optinAcceptedResult").style = "display:none;";
    document.getElementById("optinDeclinedResult").style = "display:block;";
  });

  // --- Tutorial --------------------------------------------------------------------------
  let startTutorial = false;
  let currentTutorial;

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
    currentTutorial = 1;
    if (startTutorial) {
      document.getElementById("tutorialScreens").style = "display:block;";
      document.getElementById("tutorialNavigation").style = "display:flex;";
      document.querySelector("#tutorialScreens img:nth-child(" + currentTutorial + ")").style = "display:block;";
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
      document.querySelector("#tutorialScreens img:nth-child(" + i + ")").style = "display:none;";
    }
    document.querySelector("#tutorialScreens img:nth-child(" + currentTutorial + ")").style = "display:block;";
    if (currentTutorial < 1 || currentTutorial > 7) { // Resets tutorial
      startTutorial = false;
      currentTutorial = 1;
      document.getElementById("tutorialScreens").style = "display:none;";
      document.getElementById("tutorialNavigation").style = "display:none;";
      document.getElementById("tutorial").style = "height:400px;";
    }
    if (currentTutorial === 6 || currentTutorial === 7) {
      document.getElementById("tutorialNavigation").style = "bottom:476px;";
    } else {
      document.getElementById("tutorialNavigation").style = "bottom:8px;";
    }
  }

  document.getElementById("confettiLogo").addEventListener("click", () => {
    confetti.start(1000, 150);
  });
};
