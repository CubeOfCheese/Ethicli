window.onload = function() {
    console.log("content.js");
    var dom = document.getElementsByTagName('html')[0].innerHTML;
    var present = dom.search("content=\"product\"")
    if (present == -1) {
        present = dom.search("gl-product-card-container")
    }

    if (present != -1) {
        chrome.runtime.sendMessage({ shoppingPage: true }, function(response) {});
    } else {
        chrome.runtime.sendMessage({ shoppingPage: false }, function(response) {});
    }
};