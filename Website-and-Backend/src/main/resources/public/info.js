window.onload = function() {
  var currentUrl = window.location.href;
  var urlCut = currentUrl.search("info") + 5;
  var companyName = currentUrl.substring(urlCut);
  var url = "https://ethicli.com/score/" + companyName;
  fetch(url)
  .then(response => response.json())
  .then((data) => {
    if (data.name) {
      renderPage(data);
    } else {
      displayUnavailable();
    }
  })
}

function renderPage(companyData) {
  document.getElementById("companyName").innerText = companyData.name;
  document.getElementById("overallRating").innerText = companyData.overallScore.toFixed(1);;
  document.getElementById("companyWebsite").innerText = companyData.website;
  if (companyData.website.search("http") == -1) {
    document.getElementById("companyWebsite").href = "http://" + companyData.website;
  } else {
    document.getElementById("companyWebsite").href = companyData.website;
  }
  document.getElementById("environmentRating").innerText = companyData.environmentScore.toFixed(1);;
  document.getElementById("laborRating").innerText = companyData.laborScore.toFixed(1);;
  document.getElementById("animalRating").innerText = companyData.animalsScore.toFixed(1);;
  if (companyData.bluesignPartner) {
    document.getElementById("bluesign").style = "display:block;"
  }
  if (companyData.bcorpCertified) {
    document.getElementById("bcorp").style = "display:block;"
  }
  if (companyData.blackOwnedBusiness) {
    document.getElementById("blackowned").style = "display:block;"
  }
  if (companyData.supportsBLM) {
    document.getElementById("blmsupport").style = "display:block;"
  }
  if (companyData.greenPowerPercentage) {
    document.getElementById("greenPowerPercentage").innerText
      = "Percentage of power produced by renewable energies: " + companyData.greenPowerPercentage + "%";
  }
}

function displayUnavailable() {
  // Add all changes necessary to display something that shows no data is available
  document.getElementsByTagName("h1")[0].innerText = "No info available"
}
