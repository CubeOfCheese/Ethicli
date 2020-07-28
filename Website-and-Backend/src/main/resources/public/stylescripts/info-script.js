$(document).ready(function() {
    $(window).scroll(function() {
        if ($(window).width() > 768) {
            $("#navlinks a").css({
                "background": "#102340",
            });
            $("#navlinks a").on("mouseover", function() {
                $(this).css({
                    "background": "#2B6589",
                });
            })
            $("#navlinks a").on("mouseleave", function() {
                $(this).css({
                    "background": "#102340",
                });
            })
        } else {
            $("#navlinks a").css({
                "background": "#8EA289",
            });
            $("#navlinks a").on("mouseover", function() {
                $(this).css({
                    "background": "#2B6589",
                });
            })
            $("#navlinks a").on("mouseleave", function() {
                $(this).css({
                    "background": "#8EA289",
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

    switchSection();
});

function switchSection(){
    $("#environmentalStat").click(function(){
        $(this).css({
            "background":"#2B6589",
            "border-radius":"20px 20px 0 0",
            "margin-bottom":"0"
        });
        $("#laborStat, #animalStat").css({
            "background":"#102340",
            "border-radius":"100px",
            "margin-bottom":"12px"
        });

        $("#expandedStats article").css({"border-radius":"0 20px 20px 20px"});
        $("#environmentalSec").css({"display":"block"});
        $("#laborSec").css({"display":"none"});
        $("#animalSec").css({"display":"none"});
    })

    $("#laborStat").click(function(){
        $(this).css({
            "background":"#2B6589",
            "border-radius":"20px 20px 0 0",
            "margin-bottom":"0"
        });
        $("#environmentalStat, #animalStat").css({
            "background":"#102340",
            "border-radius":"100px",
            "margin-bottom":"12px"
        });

        $("#expandedStats article").css({"border-radius":"20px"});
        $("#environmentalSec").css({"display":"none"});
        $("#laborSec").css({"display":"block"});
        $("#animalSec").css({"display":"none"});
    })

    $("#animalStat").click(function(){
        $(this).css({
            "background":"#2B6589",
            "border-radius":"20px 20px 0 0",
            "margin-bottom":"0"
        });
        $("#environmentalStat, #laborStat").css({
            "background":"#102340",
            "border-radius":"100px",
            "margin-bottom":"12px"
        });

        $("#expandedStats article").css({"border-radius":"20px"});
        $("#environmentalSec").css({"display":"none"});
        $("#laborSec").css({"display":"none"});
        $("#animalSec").css({"display":"block"});
    })
}