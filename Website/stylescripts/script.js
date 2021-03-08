$(document).ready(function() {
  $(window).scroll(function() {
    if ($(window).width() > 768 && $(window).width() <= 800) {
      if (($(this).scrollTop() < 730) || ($(this).scrollTop() > 2700)) {
        darkNav();
      } else {
        lightNav();
      }
    } else if ($(window).width() > 800 && $(window).width() <= 850) {
      if (($(this).scrollTop() < 730) || ($(this).scrollTop() > 2720)) {
        darkNav();
      } else {
        lightNav();
      }
    } else if ($(window).width() > 850) {
      if (($(this).scrollTop() < 730) || ($(this).scrollTop() > 2860)) {
        darkNav();
      } else {
        lightNav();
      }
    } else {
      $("#index #navlinks a").css({
        "background": "#8EA289",
        "color": "#FFFCF2"
      });
      $("#index #navlinks a").on("mouseover", function() {
        $(this).css({
          "background": "#2B6589",
          "color": "#FFFCF2"
        });
      });
      $("#index #navlinks a").on("mouseleave", function() {
        $(this).css({
          "background": "#8EA289",
          "color": "#FFFCF2"
        });
      });
    }

    function darkNav() {
      $("#index #navlinks a").css({
        "background": "#102340",
        "color": "#FEFCF3"
      });
      $("#index #navlinks a").on("mouseover", function() {
        $(this).css({
          "background": "#2B6589",
          "color": "#FFFCF2"
        });
      });
      $("#index #navlinks a").on("mouseleave", function() {
        $(this).css({
          "background": "#102340",
          "color": "#FEFCF3"
        });
      });
    }

    function lightNav() {
      $("#index #navlinks a").css({
        "background": "#C9D4C6",
        "color": "#102340"
      });
      $("#index #navlinks a").on("mouseover", function() {
        $(this).css({
          "background": "#2B6589",
          "color": "#FFFCF2"
        });
      });
      $("#index #navlinks a").on("mouseleave", function() {
        $(this).css({
          "background": "#C9D4C6",
          "color": "#102340"
        });
      });
    }
    showPlantDude();
  });

  showPlantDude();
  function showPlantDude() {
    if ($(window).width() < 768) {
      $("#countdownPrompt").addClass("hide");
    } else {
      if (($(this).scrollTop() > 600)) {
        $("#countdownPrompt").addClass("hide");
      } else {
        $("#countdownPrompt").removeClass("hide");
      }
    }
  }

  dropdown();
  function dropdown() {
    let hasTouchScreen = false;
    if ("maxTouchPoints" in navigator) {
      hasTouchScreen = navigator.maxTouchPoints > 0;
    } else if ("msMaxTouchPoints" in navigator) {
      hasTouchScreen = navigator.msMaxTouchPoints > 0;
    } else {
      const mQ = window.matchMedia && matchMedia("(pointer:coarse)");
      if (mQ && mQ.media === "(pointer:coarse)") {
        hasTouchScreen = !!mQ.matches;
      } else if ("orientation" in window) {
        hasTouchScreen = true; // deprecated, but good fallback
      } else { // Only as a last resort, fall back to user agent sniffing
        const UA = navigator.userAgent;
        hasTouchScreen = (
          /\b(BlackBerry|webOS|iPhone|IEMobile)\b/i.test(UA)
            || /\b(Android|Windows Phone|iPad|iPod)\b/i.test(UA)
        );
      }
    }
    if (hasTouchScreen) {
      $(".dropdownMenu").click(function() {
        $(".dropdownIcon").toggleClass("dropped");
        $(".submenu").toggleClass("submenuClicked");
      });
    } else {
      $(".dropdownMenu").hover(function() {
        $(".dropdownIcon").toggleClass("dropped");
        $(".submenu").toggleClass("submenuClicked");
      });
    }
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
    showPlantDude();
  }).trigger("resize");

  $("#mailingListSubmit").click(function() {
    alert("Our mailing list isn't available yet, but thank you for considering to subscribe!");
  });

  $("#currentYear").text(new Date().getFullYear()); // updates copyright with current year
});
