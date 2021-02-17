const HEIGHT_TUTORIAL = 600;
const HEIGHT_MESSAGING = 600;
const HEIGHT_MESSAGE_ERROR = 320;
const HEIGHT_MESSAGE_SENT = 280;
const HEIGHT_MESSAGE_SENT_EMAIL = 50;
const HEIGHT_SHOP_NAME = 40;


window.addEventListener("load", () => {
  document.getElementById("menu").addEventListener("click", () => {
    document.getElementById("menuPanel").classList.toggle("menuClicked");
  });

  document.getElementById("menuBacking").addEventListener("click", () => {
    document.getElementById("menuPanel").classList.remove("menuClicked");
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

  document.getElementById("reportIssue").addEventListener("click", () => {
    previousHeight = document.body.style.height;
    document.body.classList.add("messagingOpen");
    document.body.style = "height:" + HEIGHT_MESSAGING + "px;";
    document.getElementById("menuPanel").classList.add("hide");

    document.getElementById("messagingFormGroup").classList.remove("sendClicked");
    document.getElementById("messageSubmitted").classList.remove("success");
    document.getElementById("messageFailed").classList.remove("failed");
    // reportIssue analytics event
  });

  document.getElementById("closeMessaging").addEventListener("click", () => { // closes messaging system
    document.getElementById("menuPanel").classList.remove("hide");
    document.body.classList.remove("messagingOpen");
    document.body.style = "height:" + previousHeight;
  });

  let messagingReason;
  let messageContent;
  let validated = false;
  document.getElementById("messagingReason").addEventListener("change", () => { // updates messaging content
    getMessagingValues();
    let messagePrefill;
    switch (messagingReason) {
      case "This should be a shop":
        messagePrefill = "This is a shop page but isn't recognized";
        break;
      case "No shop rating":
        messagePrefill = "I would like to see a rating for this shop";
        break;
      case "Incorrect shop name":
        messagePrefill = "Incorrect shop name";
        break;
      case "Other":
        messagePrefill = "";
        break;
      default:
        messagePrefill = "";
    }
    if (document.getElementById("messageContent").value === "") {
      document.getElementById("messageContent").value = messagePrefill;
    }
    validate();
  });

  document.getElementById("messagingEmail").addEventListener("blur", () => {
    const userEmail = document.getElementById("messagingEmail").value;
    if ((userEmail !== "") && (userEmail !== undefined)) {
      document.getElementById("withemail").classList.add("hasEmail");
    } else {
      document.getElementById("withemail").classList.remove("hasEmail");
    }
  });

  document.getElementById("messagingReason").addEventListener("blur", () => {
    validate();
  });

  document.getElementById("messageContent").addEventListener("blur", () => {
    validate();
  });

  function getMessagingValues() {
    messageContent = document.getElementById("messageContent").value;
    messagingReason = document.getElementById("messagingReason").value;
  }

  function validate() {
    getMessagingValues();
    if ((messagingReason === "") || (messageContent === "")) {
      document.getElementById("sendMessageButton").disabled = true;
      document.getElementById("requiredError").style.display = "block";
      validated = false;
    } else {
      document.getElementById("sendMessageButton").disabled = false;
      document.getElementById("requiredError").style.display = "none";
      validated = true;
    }
  }

  document.getElementById("sendMessageButton").addEventListener("click", () => {
    if (validated) {
      sendMessage(previousHeight);
    }
    validated = false;
  });

  document.getElementById("visitWebsite").addEventListener("click", () => {
    // VisitedWebsite analytics event
  });
});

document.getElementById("reportIssue").onmouseover = () => {
  document.getElementById("reportIssueTip").style.left = (event.clientX - 30) + "px";
  document.getElementById("reportIssueTip").style.top = (event.clientY - 30) + "px";
};

// --- Fade Long URL ---------------------------------------------------------------------------

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

export function sendFeedback(messageType, userEmail) {
  const query = { active: true, currentWindow: true };
  chrome.tabs.query(query, (tabs) => {
    const currentTab = tabs[0];
    const fetchUrlFeedback = "https://ethicli.com/feedback";
    const fetchData = {
      url: currentTab.url,
      userEmail: userEmail,
      messageType: messageType
    };
    const fetchParams = {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(fetchData)
    };
    fetch(fetchUrlFeedback, fetchParams)
        .then((response) => {
          if (response.status !== 200) {
            throw new Error("404");
          }
          document.getElementById("lazyFeedback").classList.add("succeeded"); // hide first div // showing second div
        })
        .catch((error) => {
          document.getElementById("lazyFeedback").classList.add("failed"); // hide first div // showing third div
          // "RatingRequestFailed - " + error.message analytics event
        });
  });
  // ShopRequested analytics event
}


function sendMessage() {
  const userName = document.getElementById("messagingName").value;
  const userEmail = document.getElementById("messagingEmail").value;
  const userMessageReason = document.getElementById("messagingReason").value;
  const userMessage = document.getElementById("messageContent").value;

  const query = { active: true, currentWindow: true };
  chrome.tabs.query(query, (tabs) => {
    const currentTab = tabs[0];
    const fetchUrlFeedback = "https://ethicli.com/feedback";
    const fetchData = {
      url: currentTab.url,
      userName: userName,
      userEmail: userEmail,
      messageType: userMessageReason,
      message: userMessage
    };
    const fetchParams = {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(fetchData)
    };
    fetch(fetchUrlFeedback, fetchParams)
        .then(() => {
          document.getElementById("messagingFormGroup").classList.add("sendClicked");
          document.getElementById("messageSubmitted").classList.add("success");

          let responseHeight = HEIGHT_MESSAGE_SENT;
          if (document.getElementById("withemail").classList.contains("hasEmail")) {
            responseHeight += HEIGHT_MESSAGE_SENT_EMAIL;
            document.getElementById("uemail").innerText = userEmail;
          } else {
            document.getElementById("withemail").classList.remove("hasEmail");
          }
          if (!document.getElementById("messaging").classList.contains("noShopName")) {
            responseHeight += HEIGHT_SHOP_NAME;
          }
          document.body.style = "height:" + responseHeight + "px;";

          document.getElementById("sendMessageButton").disabled = true;
        })
        .catch(() => {
          document.body.style = "height:" + HEIGHT_MESSAGE_ERROR + "px;";
          document.getElementById("messagingFormGroup").classList.add("sendClicked");
          document.getElementById("messageFailed").classList.add("failed");
        });
  });
}
