// $(document).ready(function() {
// --- Tutorial --------------------------------------------------------------------------
//   let startTutorial = false;
//   let currentTutorial;
//   $("#hideTutorial").click(function() {
//     $("#tutorial").toggleClass("hideTutorial");
//     if (startTutorial) {
//       $("#tutorial").removeClass("tutorialHeightAdjust");
//     } else {
//       $("#tutorial").addClass("tutorialHeightAdjust");
//     }
//   });

//   $("#watchTutorial").click(function() {
//     startTutorial = !startTutorial;
//     currentTutorial = 1;
//     if (startTutorial) {
//       $("#tutorialScreens").css({ "display": "block" });
//       $("#tutorialNavigation").css({ "display": "flex" });
//       $("#tutorialScreens img:nth-child(" + currentTutorial + ")").css({ "display": "block" });
//       $("#tutorial").css({ "height": "624px" });
//     } else {
//       $("#tutorialScreens").css({ "display": "none" });
//       $("#tutorialNavigation").css({ "display": "none" });
//     }
//     console.log("After watchTutorial " + currentTutorial);
//   });

//   $("#tutorialBack").click(function() {
//     currentTutorial -= 1; tutorialSlideshow();
//   });
//   $("#tutorialNext").click(function() {
//     currentTutorial += 1; tutorialSlideshow();
//   });

//   function tutorialSlideshow() {
//     for (let i = 0; i < 7; i++) {
//       $("#tutorialScreens img:nth-child(" + i + ")").css({ "display": "none" });
//     }
//     $("#tutorialScreens img:nth-child(" + currentTutorial + ")").css({ "display": "block" });
//     if (currentTutorial < 1 || currentTutorial > 7) { // Resets tutorial
//       startTutorial = false;
//       currentTutorial = 1;
//       $("#tutorialScreens").css({ "display": "none" });
//       $("#tutorialNavigation").css({ "display": "none" });
//       $("#tutorial").css({ "height": "400px" });
//     }
//     if (currentTutorial === 6 || currentTutorial === 7) {
//       $("#tutorialNavigation").css({ "bottom": "476px" });
//     } else {
//       $("#tutorialNavigation").css({ "bottom": "8px" });
//     }
//   }

//   $("#confettiLogo").click(function() {
//     confetti.start(1000, 150);
//   });
// });


window.onload = () => {
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
      document.getElementById("tutorialScreens img:nth-child(" + currentTutorial + ")").style = "display:block;";
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
      document.getElementById("#tutorialScreens img:nth-child(" + i + ")").style = "display:none;";
    }
    document.getElementById("#tutorialScreens img:nth-child(" + currentTutorial + ")").style = "display:block;";
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
