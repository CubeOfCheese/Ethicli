$(document).ready(function() {
  $(window).scroll(function() {
    if ($(window).width() > 768) {
      $("#navlinks a").css({ "background": "#102340" });
      $("#navlinks a").on("mouseover", function() {
        $(this).css({ "background": "#2B6589" });
      });
      $("#navlinks a").on("mouseleave", function() {
        $(this).css({ "background": "#102340" });
      });
    } else {
      $("#navlinks a").css({ "background": "#8EA289" });
      $("#navlinks a").on("mouseover", function() {
        $(this).css({ "background": "#2B6589" });
      });
      $("#navlinks a").on("mouseleave", function() {
        $(this).css({ "background": "#8EA289" });
      });
    }
  });

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


  // Tabs Initial Setup -------------------------------------------------------------------------
  // $("#expandedStats article").css({"border-radius":"0 20px 20px 20px"})


  // Miling List and Tab Switch -----------------------------------------------------------------

  $("#mailingListSubmit").click(function() {
    alert("Our mailing list isn't available yet, but thank you for considering to subscribe!");
  });

  initialTabSetup();
  switchSection();
});

function switchSection() {
  $("#environmentalStat").click(function() {
    $(this).addClass("tabClicked");
    $("#laborStat, #animalStat, #socialStat").removeClass("tabClicked");

    $("#environmentalSec").css({ "display": "block" });
    $("#laborSec, #animalSec, #socialSec").css({ "display": "none" });
  });

  $("#laborStat").click(function() {
    $(this).addClass("tabClicked");
    $("#environmentalStat, #animalStat, #socialStat").removeClass("tabClicked");

    $("#laborSec").css({ "display": "block" });
    $("#environmentalSec, #animalSec, #socialSec").css({ "display": "none" });
  });

  $("#animalStat").click(function() {
    $(this).addClass("tabClicked");
    $("#environmentalStat, #laborStat, #socialStat").removeClass("tabClicked");

    $("#animalSec").css({ "display": "block" });
    $("#environmentalSec, #laborSec, #socialSec").css({ "display": "none" });
  });

  $("#socialStat").click(function() {
    $(this).addClass("tabClicked");
    $("#environmentalStat, #laborStat, #animalStat").removeClass("tabClicked");

    $("#socialSec").css({ "display": "block" });
    $("#environmentalSec, #laborSec, #animalSec").css({ "display": "none" });
  });
}

function initialTabSetup() { // highlights first displayed tab
  if ($("#environmentalStat").is(":visible")) {
    $("#environmentalStat").addClass("tabClicked");
    $("#environmentalSec").css({ "display": "block" });
    $("#laborSec, #animalSec, #socialSec").css({ "display": "none" });
  } else if ($("#laborStat").is(":visible")) {
    $("#laborStat").addClass("tabClicked");
    $("#laborSec").css({ "display": "block" });
    $("#environmentalSec, #animalSec, #socialSec").css({ "display": "none" });
  } else if ($("#animalStat").is(":visible")) {
    $("#animalStat").addClass("tabClicked");
    $("#animalSec").css({ "display": "block" });
    $("#environmentalSec, #laborSec, #socialSec").css({ "display": "none" });
  } else if ($("#socialStat").is(":visible")) {
    $("#socialStat").addClass("tabClicked");
    $("#socialSec").css({ "display": "block" });
    $("#environmentalSec, #laborSec, #animalSec").css({ "display": "none" });
  }
}
