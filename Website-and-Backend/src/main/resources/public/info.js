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
  document.getElementById("overallRating").innerText = companyData.overallScore.toFixed(1);
  document.getElementById("companyWebsite").innerText = companyData.website;
  if (companyData.website.search("http") == -1) {
    document.getElementById("companyWebsite").href = "http://" + companyData.website;
  } else {
    document.getElementById("companyWebsite").href = companyData.website;
  }
  document.getElementById("environmentRating").innerText = companyData.environmentScore.toFixed(1);
  document.getElementById("laborRating").innerText = companyData.laborScore.toFixed(1);
  document.getElementById("animalRating").innerText = companyData.animalsScore.toFixed(1);
  document.getElementById("socialRating").innerText = companyData.socialScore.toFixed(1);
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
    document.getElementById("greenPowerPercentage").innerText = companyData.greenPowerPercentage + "%";
  }

  // if(companyData.environmentScore.toFixed(1) == 0){
  //   document.getElementById("environmentalStat").style = "display:none;"
  //   document.getElementById("environmentalSec").style = "display:none;"
  // }
  // if(companyData.laborScore.toFixed(1) == 0){
  //   document.getElementById("laborStat").style = "display:none;"
  //   document.getElementById("laborSec").style = "display:none;"
  // }
  // if(companyData.animalsScore.toFixed(1) == 0){
  //   document.getElementById("animalStat").style = "display:none;"
  //   document.getElementById("animalSec").style = "display:none;"
  // }
  // if(companyData.socialScore.toFixed(1) == 0){
  //   document.getElementById("socialStat").style = "display:none;"
  //   document.getElementById("socialSec").style = "display:none;"
  // }

  // Subscores ----------------------------------------------------------------------------------------
  if(companyData.environmentScore.toFixed(1) == 0){
    document.getElementById("environmentalStat").style = "display:none;"
    document.getElementById("environmentalSec").style = "display:none;"
  }else{
    document.getElementById("environmentalStat").classList.add("tabClicked");
    document.getElementById("environmentalSec").style = "display:block;"
  }

  if(companyData.laborScore.toFixed(1) == 0){
    document.getElementById("laborStat").style = "display:none;"
    document.getElementById("laborSec").style = "display:none;"
  }else{
    if(companyData.environmentScore.toFixed(1) == 0){
      document.getElementById("laborStat").classList.add("tabClicked");
      document.getElementById("laborSec").style = "display:block;"
    }
  }

  if(companyData.animalsScore.toFixed(1) == 0){
    document.getElementById("animalStat").style = "display:none;"
    document.getElementById("animalSec").style = "display:none;"
  }else{
    if((companyData.environmentScore.toFixed(1) == 0)&&(companyData.laborScore.toFixed(1) == 0)){
      document.getElementById("animalStat").classList.add("tabClicked");
      document.getElementById("animalSec").style = "display:block;"
    }
  }

  if(companyData.socialScore.toFixed(1) == 0){
    document.getElementById("socialStat").style = "display:none;"
    document.getElementById("socialSec").style = "display:none;"
  }else{
    if((companyData.environmentScore.toFixed(1) == 0)&&(companyData.laborScore.toFixed(1) == 0)&&(companyData.animalsScore.toFixed(1) == 0)){
      document.getElementById("socialStat").classList.add("tabClicked");
      document.getElementById("socialSec").style = "display:block;"
    }
  }
}

function displayUnavailable() {
  // Add all changes necessary to display something that shows no data is available
  document.getElementsByTagName("h1")[0].innerText = "No info available";
  document.getElementById("overallRating").innerText = "🙊";
  document.getElementById("overall-score-desc").innerHTML =
    'It looks like we don&#39t have any info on that company. We&#39re constantly trying to improve our data coverage, \
    but it looks like we&#39ve still got farther to go. Please send us an email at \
    <a href="mailto:&#104;&#101;&#108;&#108;&#111;&#064;&#101;&#116;&#104;&#105;&#099;&#108;&#105;&#046;&#099;&#111;&#109;?subject=Ethicli Inquiry" target="_blank">hello@ethicli.com</a> \
    to let us know if you really want to know more about this company. Thank you for shopping Ethicli 💛';
  document.getElementById("environmentalStat").style = "display:none;"
  document.getElementById("laborStat").style = "display:none;"
  document.getElementById("animalStat").style = "display:none;"
  document.getElementById("socialStat").style = "display:none;"
}
