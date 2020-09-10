$(document).ready(function() {
//--- Optin --------------------------------------------------------------------------
    $("#optinAccepted").click(function() {
        $("#optinAcceptedResult").css({"display":"block"});
        $("#optinDeclinedResult").css({"display":"none"});
    })

    $("#optinDeclined").click(function() {
        $("#optinAcceptedResult").css({"display":"none"});
        $("#optinDeclinedResult").css({"display":"block"});
    })

//--- Tutorial --------------------------------------------------------------------------
    var startTutorial = false;
    var currentTutorial;
    $("#hideTutorial").click(function() {
        $("#tutorial").toggleClass("hideTutorial");
        if(startTutorial) {
            $("#tutorial").removeClass("tutorialHeightAdjust");
        } else {
            $("#tutorial").addClass("tutorialHeightAdjust");
        }
    })

    $("#watchTutorial").click(function() { 
        startTutorial = !startTutorial 
        currentTutorial = 1;
        if(startTutorial) {
            $("#tutorialScreens").css({"display":"block"});
            $("#tutorialNavigation").css({"display":"flex"});
            $("#tutorialScreens img:nth-child("+currentTutorial+")").css({"display":"block"});
            $("#tutorial").css({"height":"624px"});
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
            $("#tutorial").css({"height":"400px"});
        }
        if(currentTutorial == 6 || currentTutorial == 7) {
            $("#tutorialNavigation").css({"bottom":"476px"});
        } else {
            $("#tutorialNavigation").css({"bottom":"8px"});
        }
    }

    $("#confettiLogo").click(function() { confetti.start(1000, 100); })
});
