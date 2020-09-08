$(document).ready(function() {
    $(window).scroll(function() {
        if ($(window).width() > 768 && $(window).width() <= 800) {
            if (($(this).scrollTop() < 730) || ($(this).scrollTop() > 2870)) {
                darkNav();
            } else {
                lightNav();
            }
        } else if ($(window).width() > 800 && $(window).width() <= 850) {
            if (($(this).scrollTop() < 730) || ($(this).scrollTop() > 2900)) {
                darkNav();
            } else {
                lightNav();
            }
        } else if ($(window).width() > 850) {
            if (($(this).scrollTop() < 730) || ($(this).scrollTop() > 2960)) {
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
            })
            $("#index #navlinks a").on("mouseleave", function() {
                $(this).css({
                    "background": "#8EA289",
                    "color": "#FFFCF2"
                });
            })
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
            })
            $("#index #navlinks a").on("mouseleave", function() {
                $(this).css({
                    "background": "#102340",
                    "color": "#FEFCF3"
                });
            })
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
            })
            $("#index #navlinks a").on("mouseleave", function() {
                $(this).css({
                    "background": "#C9D4C6",
                    "color": "#102340"
                });
            })
        }
    });

    dropdown();
    function dropdown() {
        $(".dropdownMenu").click(function(){
            $(".dropdownIcon").toggleClass("dropped");
            $(".submenu").toggleClass("submenuClicked");
        });
    }

    mobileNav(); //allows menu to toggle when starting at a mobile size
    function mobileNav() { //create mobile menu toggle to show navigation links
        $("#triplebar").click(function(){
            $("#navlinks").toggleClass("navClicked");
        })
    }
    $(window).bind("resize", function () {
        if ($(this).width() > 768) {
            $("#navlinks").removeClass("navClicked");
            $(".submenu").removeClass("submenuClicked");
            $(".dropdownIcon").removeClass("dropped");
        }
    }).trigger('resize');


//--- Tutorial --------------------------------------------------------------------------
    $("#hideTutorial").click(function() {
        $("#tutorial").toggleClass("hideTutorial");
    })

    var startTutorial = false;
    var currentTutorial;
    $("#watchTutorial").click(function() { 
        startTutorial = !startTutorial 
        currentTutorial = 1;
        if(startTutorial) {
            $("#tutorialScreens").css({"display":"block"});
            $("#tutorialNavigation").css({"display":"flex"});
            $("#tutorialScreens img:nth-child("+currentTutorial+")").css({"display":"block"});
        } else {
            $("#tutorialScreens").css({"display":"none"});
            $("#tutorialNavigation").css({"display":"none"});
        }
        console.log("After watchTutorial "+currentTutorial)
    })

    $("#tutorialBack").click(function() { currentTutorial -= 1; tutorialSlideshow(); })
    $("#tutorialNext").click(function() { currentTutorial += 1; tutorialSlideshow(); })
    function tutorialSlideshow() {
        for(i=0; i<7; i++){
            $("#tutorialScreens img:nth-child("+i+")").css({"display":"none"});
        }
        $("#tutorialScreens img:nth-child("+currentTutorial+")").css({"display":"block"});
        if(currentTutorial < 1 || currentTutorial > 7) { // Resets tutorial
            startTutorial = false;
            currentTutorial = 1;
            $("#tutorialScreens").css({"display":"none"});
            $("#tutorialNavigation").css({"display":"none"});
        }
        if(currentTutorial == 6 || currentTutorial == 7) {
            $("#tutorialNavigation").css({"bottom":"476px"});
        } else {
            $("#tutorialNavigation").css({"bottom":"8px"});
        }
    }

    $("#confettiLogo").click(function() { confetti.start(1000, 100); })

    $("#mailingListSubmit").click(function(){
        alert("Our mailing list isn't available yet, but thank you for considering to subscribe!");
    });
});
