$(document).ready(function() {
    $(window).scroll(function() {
        if ($(window).width() > 768) {
            $("#navlinks a").css({"background": "#102340"});
            $("#navlinks a").on("mouseover", function() {
                $(this).css({"background": "#2B6589"});
            })
            $("#navlinks a").on("mouseleave", function() {
                $(this).css({"background": "#102340"});
            })
        } else {
            $("#navlinks a").css({"background": "#8EA289",});
            $("#navlinks a").on("mouseover", function() {
                $(this).css({"background": "#2B6589"});
            })
            $("#navlinks a").on("mouseleave", function() {
                $(this).css({"background": "#8EA289"});
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

    mobileNav(); //allows menu to toggle when starting off with a mobile size
    $(window).resize(function(){ //after window is resized, allow menu toggle
        mobileNav();
    })


    // Tabs Initial Setup -------------------------------------------------------------------------
    //$("#expandedStats article").css({"border-radius":"0 20px 20px 20px"})


    // Miling List and Tab Switch -----------------------------------------------------------------

    $("#mailingListSubmit").click(function(){
        alert("Our mailing list isn't available yet, but thank you for considering to subscribe!");
    });

    switchSection();
});

function switchSection(){
    $("#environmentalStat").click(function(){
        $(this).addClass("tabClicked");
        $("#laborStat, #animalStat, #socialStat").removeClass("tabClicked");

        $("#environmentalSec").css({"display":"block"});
        $("#laborSec, #animalSec, #socialSec").css({"display":"none"});
    })

    $("#laborStat").click(function(){
        $(this).addClass("tabClicked");
        $("#environmentalStat, #animalStat, #socialStat").removeClass("tabClicked");

        $("#laborSec").css({"display":"block"});
        $("#environmentalSec, #animalSec, #socialSec").css({"display":"none"});
    })

    $("#animalStat").click(function(){
        $(this).addClass("tabClicked");
        $("#environmentalStat, #laborStat, #socialStat").removeClass("tabClicked");
        
        $("#animalSec").css({"display":"block"});
        $("#environmentalSec, #laborSec, #socialSec").css({"display":"none"});
    })

    $("#socialStat").click(function(){
        $(this).addClass("tabClicked");
        $("#environmentalStat, #laborStat, #animalStat").removeClass("tabClicked");
        
        $("#socialSec").css({"display":"block"});
        $("#environmentalSec, #laborSec, #animalSec").css({"display":"none"});
    })
}