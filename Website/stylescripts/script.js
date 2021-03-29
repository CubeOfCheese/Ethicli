$(document).ready(function() {
  $(function() { // Load Nav and Footer
    $("#nav").load("./components/nav.html");
    $("#nav_404").load("./views/components/nav.html");
    $("#footer").load("./components/footer.html");
    $("#footer_404").load("./views/components/footer.html");
  });

  $("#currentYear").text(new Date().getFullYear()); // updates copyright with current year
});
