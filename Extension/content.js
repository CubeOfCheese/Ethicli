var title = document.title;
console.log(title);

window.onload = function() {
  var dom = document.getElementsByTagName('html')[0].innerHTML;
  console.log(dom);
  var present = dom.search("content=\"product\"")
  if (present == -1) {
    present = dom.search("gl-product-card-container")
  }
  console.log(present);
  if (present != -1) {
    console.log("found");
    chrome.runtime.sendMessage({shoppingPage: true}, function(response) {
      console.log(response.acknowledge);
    });
  }
  else {
    console.log("not found");
    chrome.runtime.sendMessage({shoppingPage: false}, function(response) {
      console.log(response.acknowledge);
    });
  }
};
