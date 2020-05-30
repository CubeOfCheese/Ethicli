window.onload = function() {
    var dom = document.getElementsByTagName('html')[0].innerHTML;

    var shopWords = [
        "content=\"product\"",
        "gl-product-card-container",
        "product-card",
        "product-declinaison",
        "product-detail__buy",
        "product-detail__desc",
        "product-detail__details",
        "product-detail__images",
        "product-images",
        "product-info",
        "product-name",
        "shopify-section"
    ];

    var wordTracker = 0;
    for (i = 0; i < shopWords.length; i++) {
        var present = dom.search(shopWords[i]);
        if (present > -1) { // if words are present, add values.
            wordTracker += 1;
        }
    }

    if (wordTracker > 0) { //if there's at least one shopWord present
        chrome.runtime.sendMessage({ shoppingPage: true }, function(response) {});
    } else {
        chrome.runtime.sendMessage({ shoppingPage: false }, function(response) {});
    }
};
