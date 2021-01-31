const HEIGHT_TUTORIAL = 600;
const HEIGHT_MESSAGING = 600;


window.addEventListener("load", () => {
  document.getElementById("menu").addEventListener("click", () => {
    document.getElementById("menuPanel").classList.toggle("menuClicked");
  });

  document.getElementById("menuBacking").addEventListener("click", () => {
    document.getElementById("menuPanel").classList.remove("menuClicked");
  });

  document.getElementById("somethingWrong").addEventListener("click", () => {
    document.getElementById("messaging").classList.add("show");
    document.body.style = "height:" + HEIGHT_MESSAGING + "px;";
    document.getElementById("menuPanel").classList.add("hide");
    // SomethingWrong analytics event
  });
  document.getElementById("sendMessageButton").addEventListener("click", () => {
    sendMessage();
  });


  if (document.getElementById("sitename") != null) {
    fadeLongURL();
  }

  // --- Tutorial --------------------------------------------------------------------------
  let startTutorial = false;
  let currentTutorial;
  let previousHeight;

  document.getElementById("watchTutorial").addEventListener("click", () => {
    previousHeight = document.body.style.height;
    document.body.style = "height:" + HEIGHT_TUTORIAL + "px;";
    startTutorial = !startTutorial;
    currentTutorial = 1;
    if (startTutorial) {
      document.getElementById("tutorial").style = "display: block";
      document.getElementById("tutorialNavigation").style = "display:flex";
      document.getElementById("tutorial" + currentTutorial).style = "display:block";
    } else {
      document.getElementById("tutorialScreens").style = "display:none";
      document.getElementById("tutorialNavigation").style = "display:none";
    }
    // TutorialViewed analytics event
  });

  let idname = "tutorial1";
  document.getElementById("tutorialBack").addEventListener("click", () => {
    currentTutorial -= 1;
    tutorialSlideshow();
  });
  document.getElementById("tutorialNext").addEventListener("click", () => {
    currentTutorial += 1;
    tutorialSlideshow();
  });

  function tutorialSlideshow() {
    idname = "tutorial" + (currentTutorial);
    for (let i = 0; i < 7; i++) {
      const tutorialCycle = "tutorial" + (i + 1);
      document.getElementById(tutorialCycle).style = "display:none";
    }
    if (currentTutorial < 1 || currentTutorial > 7) { // Resets tutorial
      startTutorial = false;
      currentTutorial = 1;
      document.getElementById("tutorial").style = "display:none";
      document.getElementById("tutorialNavigation").style = "display:none";
      document.body.style = "height:" + previousHeight;
    } else {
      document.getElementById(idname).style = "display:block";
      document.body.style = "height:" + HEIGHT_TUTORIAL + "px;";
    }

    if (currentTutorial === 6 || currentTutorial === 7) {
      document.getElementById("tutorialNavigation").style = "display:flex; bottom:496px;";
    } else {
      document.getElementById("tutorialNavigation").style = "display:flex; bottom:24px;";
    }
  }

  // --- Feedback ---------------------------------------------------------------------------

  document.getElementById("closeMessaging").addEventListener("click", () => { // closes messaging system
    document.getElementById("messaging").classList.remove("show");
    document.getElementById("menuPanel").classList.remove("hide");
  });

  document.getElementById("messagingReason").addEventListener("change", () => { // closes messaging system
    const messagingReason = document.getElementById("messagingReason").value;
    let messagePrefill;
    switch (messagingReason) {
      case "This should be a shop":
        messagePrefill = "This should be a shop";
        break;
      case "No shop rating":
        messagePrefill = "No shop rating";
        break;
      case "Incorrect shop name":
        messagePrefill = "Incorrect shop name";
        break;
      case "Other":
        messagePrefill = "Other";
        break;
      default:
        messagePrefill = "";
    }
    if (document.getElementById("messageContent").value === "") {
      document.getElementById("messageContent").value = messagePrefill;
    }
  });

  document.getElementById("visitWebsite").addEventListener("click", () => {
    // VisitedWebsite analytics event
  });
});

function fadeLongURL() {
  document.getElementById("siteurl").addEventListener("mouseover", () => {
    const SHOPNAME = document.getElementById("siteurl").innerHTML;
    const siteurlLength = SHOPNAME.length + 16;
    if (siteurlLength > 40) {
      document.getElementById("siteurl").style = "margin-left: -" + (siteurlLength) + "px;";
      document.getElementById("siteurlcontainer").style
                = `-webkit-mask-image: linear-gradient(to right, transparent 0%, black 5%, black 100%, transparent 100%);
                mask-image: linear-gradient(to right, transparent 0%, black 5%, black 100%, transparent 100%)`;
    }
  });
  document.getElementById("siteurl").addEventListener("mouseout", () => {
    document.getElementById("siteurl").style = "margin-left: 0px;";
    document.getElementById("siteurlcontainer").style
            = `-webkit-mask-image: linear-gradient(to right, black 90%, transparent 100%);
            mask-image: linear-gradient(to right, black 90%, transparent 100%)`;
  });
}


function sendMessage() {
  const userName = document.getElementById("messagingName").value;
  const userMessage = document.getElementById("messageContent").value;
  const userEmail = document.getElementById("messagingEmail").value;
  const userMessageReason = document.getElementById("messagingReason").value;

  const query = { active: true, currentWindow: true };
  chrome.tabs.query(query, (tabs) => {
    const currentTab = tabs[0];
    const fetchUrlFeedback = "https://ethicli.com/feedback";
    const fetchData = {
      url: currentTab.url,
      userName: userName,
      userEmail: userEmail,
      message: userMessage,
      messageType: userMessageReason
    };
    const fetchParams = {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(fetchData)
    };
    fetch(fetchUrlFeedback, fetchParams);
  });
}
