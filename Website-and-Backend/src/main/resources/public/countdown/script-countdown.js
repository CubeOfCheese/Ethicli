$(document).ready(() => {
  // Dropdown -------------------------------------------------------------------------------------------
  dropdown();
  function dropdown() {
    $(".dropdownMenu").click(function() {
      $(".dropdownIcon").toggleClass("dropped");
      $(".submenu").toggleClass("submenuClicked");
    });
  }

  mobileNav(); // allows menu to toggle when starting at a mobile size
  function mobileNav() { // create mobile menu toggle to show navigation links
    $("#triplebar").click(function() {
      $("#navlinks").toggleClass("navClicked");
    });
  }
  $(window).bind("resize", function() {
    if ($(this).width() > 768) {
      $("#navlinks").removeClass("navClicked");
      $(".submenu").removeClass("submenuClicked");
      $(".dropdownIcon").removeClass("dropped");
    }
  }).trigger("resize");

  // Making Days -------------------------------------------------------------------------------------------
  for (let i = 2; i <= 31; i++) {
    makeDay(i);
  }
  document.querySelector("#daytitle:first-of-type").innerText = "1";

  function makeDay(dayID) {
    document.getElementById("daytitle").innerText = dayID;
    const day = document.getElementById("day1");
    const newday = day.cloneNode(true);
    newday.id = "day" + dayID;
    document.getElementById("calendar").appendChild(newday);
  }

  // Snow -------------------------------------------------------------------------------------------
  $("#snowman").click(function() {
    $(this).toggleClass("off");
    snowStorm.toggleSnow();
  });

  // Load Popup Contents -------------------------------------------------------------------------------------------
  const popupDailyMessage = [];
  const popupName = [];
  const popupLink = [];
  const popupSocialName = [];
  const popupSocialDescription = [];
  const popupSocialURL = [];
  $.getJSON("countdown/countdown.json", function(json) {
    for (let i = 0; i < 31; i++) {
      const day = Object.values(json);
      popupName.push(day[i].popupName);
      popupLink.push(day[i].popupLink);
      popupDailyMessage.push(day[i].popupDailyMessage);
      popupSocialName.push(day[i].popupSocialName);
      popupSocialDescription.push(day[i].popupSocialDescription);
      popupSocialURL.push(day[i].popupSocialURL);
    }
  });

  // Unlock Days -------------------------------------------------------------------------------------------
  const today = new Date();
  const year = today.getFullYear();
  today.getDate(); // returns 29 for today

  $(".day").mouseover(function() {
    const plantdudes = [
      "countdown/plantdudes/plantdude-red.png",
      "countdown/plantdudes/plantdude-blue.png",
      "countdown/plantdudes/plantdude-green.png",
      "countdown/plantdudes/plantdude-lightgreen.png"
    ];
    const randomplantdude = plantdudes[Math.floor(Math.random() * plantdudes.length)];
    for (let i = 0; i <= 31; i++) {
      const day = "#day" + (i + 1);
      if ($(this).is(day)) {
        if (i < today.getDate()) {
          $(this).css({ "background-image": "url('" + randomplantdude + "')" });
        } else {
          $(this).css({ "background-image": "url('countdown/plantdudes/happyplantdude.png')" });
          $(this).addClass("lockedDay");
        }
      }
    }
  });
  $(".day").mouseleave(function() {
    $(this).css({ "background-image": "url('countdown/plantdudes/happyplantdude.png')" });
    $(this).removeClass("lockedDay");
  });

  // General Popup -------------------------------------------------------------------------------------------
  $(".day").click(function() {
    for (let i = 0; i <= 31; i++) {
      const day = "#day" + (i + 1);
      if ($(this).is(day)) {
        if (today.getDate() > i || year > 2020) { // only "unlocks" days up till the current day
          $("#popupName").text(popupName[i]);
          $("#popupLink").attr("href", popupLink[i]);
          $("#popupPreview").attr("src", popupLink[i]);
          $("#popupDailyMessage").text(popupDailyMessage[i]);
          $("#popupSocialName, #popupSocialName2").text(popupSocialName[i]);
          $("#popupSocialDescription").text(popupSocialDescription[i]);
          $("#popupSocialURL, #popupSocialURL2").attr("href", popupSocialURL[i]);

          $("#popup").addClass("show");
          $("#popupLocked").removeClass("show");
          if ($(this).is("#day25")) {
            confetti.special("xmas"); confetti.start(2000, 50);
          } else if ($(this).is("#day31")) {
            confetti.special("newyears"); confetti.start(2000, 50);
            movePlantDude("newyears");
          } else {
            movePlantDude();
          }
        } else {
          $(this).css({ "background-image": "url('countdown/plantdudes/happyplantdude.png')" });
          $("#popupLocked").addClass("show");
          $("#popup").removeClass("show");
        }
      }
    }
  });

  function movePlantDude(eventDude) {
    if (eventDude === "newyears") {
      $("#ogplantdude").addClass("newyears");
    }
    $("#ogplantdude").addClass("show");
  }

  $(".closePopup").click(function() {
    $("#popup, #popupLocked").removeClass("show");
    $("#ogplantdude").removeClass("show newyears");
  });

  $("#ogplantdude").click(function() {
    alert("Tee hee");
  });
});
