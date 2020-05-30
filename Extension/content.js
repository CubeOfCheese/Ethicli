window.onload = function() {
    console.log("content.js");
    var dom = document.getElementsByTagName('html')[0].innerHTML;

    var shopWords = ["content=\"product\"", "gl-product-card-container"];
    var wordTracker = 0;
    for (i = 0; i < shopWords.length; i++) {
        var present = dom.search(shopWords[i]);
        if (present > -1) { // if words are present, add values.
            wordTracker += 1;
        }
        console.log("present: " + present.toString());
    }
    console.log("wordTracker: " + wordTracker.toString());

    if (wordTracker > 0) { //if there's at least one shopWord present
        chrome.runtime.sendMessage({ shoppingPage: true }, function(response) {});
    } else {
        chrome.runtime.sendMessage({ shoppingPage: false }, function(response) {});
    }
};