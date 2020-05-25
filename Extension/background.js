// chrome.browserAction.disable()

// console.log("hi")
// // var mySiteUrl = "https://www.google.com";
// // chrome.tabs.getCurrent(function (tabs) {
// //     console.log(tabs[0]);
// //     console.log(tabs[0].url);
// //     if(tabs[0].url === mySiteUrl){
// //       chrome.browserAction.enable()
// //     }
// // });
// chrome.tabs.onUpdated.addListener(checkURL());
//
// function checkURL() {
//   chrome.tabs.query(
//     {active:true,windowType:"normal", currentWindow: true},
//     function(d){console.log(d);
//   });
// }
var companyName = "adida";

var request = new XMLHttpRequest()
var url = 'http://localhost:8080/score/' + companyName;
request.open('GET', url, true)
request.onload = function() {
  console.log(this.response);
  if (this.response == "true") {
    chrome.browserAction.setBadgeText({text:"Yay"});
  }
  else {
    chrome.browserAction.setBadgeText({text:"Poo"});
  }
}
request.send()
