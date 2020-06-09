$(document).ready(function() {
    $(window).scroll(function() {
        if ($(window).width() > 768) {
            if (($(this).scrollTop() < 730) || ($(this).scrollTop() > 2600)) {
                $("#navlinks a").css({
                    "background": "#102340",
                    "color": "#FEFCF3"
                });
                $("#navlinks a").on("mouseover", function() {
                    $(this).css({
                        "background": "#2B6589",
                        "color": "#FFFCF2"
                    });
                })
                $("#navlinks a").on("mouseleave", function() {
                    $(this).css({
                        "background": "#102340",
                        "color": "#FEFCF3"
                    });
                })
            } else {
                $("#navlinks a").css({
                    "background": "#C9D4C6",
                    "color": "#102340"
                });
                $("#navlinks a").on("mouseover", function() {
                    $(this).css({
                        "background": "#2B6589",
                        "color": "#FFFCF2"
                    });
                })
                $("#navlinks a").on("mouseleave", function() {
                    $(this).css({
                        "background": "#C9D4C6",
                        "color": "#102340"
                    });
                })
            }
        } else {
            $("#navlinks a").css({
                "background": "#8EA289",
                "color": "#FFFCF2"
            });
            $("#navlinks a").on("mouseover", function() {
                $(this).css({
                    "background": "#2B6589",
                    "color": "#FFFCF2"
                });
            })
            $("#navlinks a").on("mouseleave", function() {
                $(this).css({
                    "background": "#8EA289",
                    "color": "#FFFCF2"
                });
            })
        }
    });
});