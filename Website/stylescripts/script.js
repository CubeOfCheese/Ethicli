$(document).ready(function() {
  $(function() { // Load Nav and Footer
    $("#nav").load("./components/nav.html");
    $("#footer").load("./components/footer.html");
  });

  $("#currentYear").text(new Date().getFullYear()); // updates copyright with current year
});
