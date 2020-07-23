$(document).ready(function() {
    $(window).scroll(function() {
        if ($(window).width() > 768 && $(window).width() <= 800) {
            if (($(this).scrollTop() < 730) || ($(this).scrollTop() > 2320)) {
                darkNav();
            } else {
                lightNav();
            }
        } else if ($(window).width() > 800 && $(window).width() <= 850) {
            if (($(this).scrollTop() < 730) || ($(this).scrollTop() > 2380)) {
                darkNav();
            } else {
                lightNav();
            }
        } else if ($(window).width() > 850) {
            if (($(this).scrollTop() < 730) || ($(this).scrollTop() > 2450)) {
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

        function darkNav(){
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

        function lightNav(){
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

    function mobileNav(){ //create mobile menu toggle to show navigation links
        if ($(window).width() <= 768) {
            $("#triplebar").click(function(){
                $("#navlinks").toggleClass("navClicked");
            })
        }else{
            $("#navlinks").removeClass("navClicked");
        }
    }

    $(window).resize(function(){ //after window is resized, allow menu toggle
        mobileNav();
    })
    mobileNav(); //allows menu to toggle when starting at a mobile size

    $("#mailingListSubmit").click(function(){
        alert("Our mailing list isn't available yet, but thank you for considering to subscribe!");
    });
});
