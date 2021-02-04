const HEIGHT_TUTORIAL = 600;

window.addEventListener("load", () => {
  document.getElementById("menu").addEventListener("click", () => {
    document.getElementById("menuPanel").classList.toggle("menuClicked");
  });

  document.getElementById("menuBacking").addEventListener("click", () => {
    document.getElementById("menuPanel").classList.remove("menuClicked");
  });

  document.getElementById("somethingWrong").addEventListener("click", () => {
    somethingWrong();
    // SomethingWrong analytics event
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
          // confetti.start(1000, 60);
        })
        .catch((error) => {
          alert(error);
          document.getElementById("lazyFeedback").classList.add("failed"); // hide first div // showing third div
          // "RatingRequestFailed - " + error.message analytics event
        });
  });
  // ShopRequested analytics event
}

function somethingWrong() {
  const query = { active: true, currentWindow: true };
  chrome.tabs.query(query, (tabs) => {
    const currentTab = tabs[0];
    const fetchUrlFeedback = "https://ethicli.com/feedback";
    const fetchData = {
      url: currentTab.url
    };
    const fetchParams = {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(fetchData)
    };
    fetch(fetchUrlFeedback, fetchParams);

    // Pulls and sets email
    document.getElementById("sendEmail").href = sendEmail();

    function sendEmail() {
      const emailUrl = "mailto:hello@ethicli.com?subject=Error%20With%20Current%20Website%20&body=Error%20with%20the%20following%20page:%20"
        + currentTab.url + "%0d%0aPlease%20let%20us%20know%20what%20is%20wrong%20below.";
      chrome.tabs.create({ url: emailUrl }, (tab) => {
        setTimeout(() => {
          chrome.tabs.remove(tab.id);
        }, 500);
      });
    }
  });
}
