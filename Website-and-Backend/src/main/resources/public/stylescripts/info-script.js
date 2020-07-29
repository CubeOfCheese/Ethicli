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

    $("#mailingListSubmit").click(function(){
        alert("Our mailing list isn't available yet, but thank you for considering to subscribe!");
    });

    switchSection();
});


$("#expandedStats article").css({"border-radius":"0 20px 20px 20px"})
function switchSection(){
    $("#environmentalStat").click(function(){
        $(this).addClass("tabClicked");
        $("#laborStat, #animalStat").removeClass("tabClicked");
        $("#expandedStats article").css({
            "border-radius":"0 20px 20px 20px"
        })

        $("#environmentalSec").css({"display":"block"});
        $("#laborSec, #animalSec").css({"display":"none"});
    })

    $("#laborStat").click(function(){
        $(this).addClass("tabClicked");
        $("#environmentalStat, #animalStat").removeClass("tabClicked");
        $("#expandedStats article").css({
            "border-radius":"20px"
        })

        $("#laborSec").css({"display":"block"});
        $("#environmentalSec, #animalSec").css({"display":"none"});
    })

    $("#animalStat").click(function(){
        $(this).addClass("tabClicked");
        $("#environmentalStat, #laborStat").removeClass("tabClicked");
        $("#expandedStats article").css({
            "border-radius":"20px"
        })
        
        $("#animalSec").css({"display":"block"});
        $("#environmentalSec, #laborSec").css({"display":"none"});
    })
}