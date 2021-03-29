$(document).ready(function() {
  $(function() { // Load Nav and Footer
    $("#nav").load("../components/nav.html");
    $("#nav_404").load("./components/nav.html");
    $("#footer").load("../components/footer.html");
    $("#footer_404").load("./components/footer.html");
  });
  $("#currentYear").text(new Date().getFullYear()); // updates copyright with current year
});
