window.onload = function pageEval() {
    let dom = document.getElementsByTagName('html')[0].innerHTML;
    var shopWords = [
        "content=\"product\"",
        /"add-to-basket"/i,
        /"addtocartBtn"/i,
        /"gl-product-card-container"/i,
        /"instock"/i,
        /"in-stock"/i,
        /"js-detail-product"/i,
        /"product-block"/i,
        /"product-block-js"/i,
        /"product-card"/i,
        /"product-container"/i,
        /"product-content"/i,
        /"product-declinaison"/i,
        /"product-detail"/i,
        /"product-detail__buy"/i,
        /"product-detail__desc"/i,
        /"product-detail__details"/i,
        /"product-detail__images"/i,
        /"product-gallery"/i,
        /"product-gallery*"/i,
        /"product-grid__content"/i,
        /"product-grid__container"/i,
        /"product-grid__image"/i,
        /"product-grid__intro"/i,
        /"product-grid__item"/i,
        /"product-grid__link"/i,
        /"product-grid*"/i,
        /"product-image"/i,
        /"product-image*"/i,
        /"product-image-container"/i,
        /"product--image-container"/i,
        /"product-image-photo"/i,
        /"product-image-wrapper"/i,
        /"product-image__link"/i,
        /"product-images"/i,
        /"product-images*"/i,
        /"product-images-container"/i,
        /"product-info"/i,
        /"product-info-block"/i,
        /"productitem"/i,
        /"productitem_imagewrapper"/i,
        /"product-item-info"/i,
        /"product-list"/i,
        /"product-list*"/i,
        /"product-listing*"/i,
        /"product-listing__content"/i,
        /"product-listing-item__wrapper"/i,
        /"product-listing__wrapper"/i,
        /"productlistwrapper"/i,
        /"product-name"/i,
        /"product-options"/i,
        /"product-options*"/i,
        /"product-options-wrapper"/i,
        /"product-page"/i,
        /"product-price"/i,
        /"product-primary"/i,
        /"product-primary*"/i,
        /"product-primary-image"/i,
        /"product-slider"/i,
        /"product-slider-container"/i,
        /"productthumbnail"/i,
        /"productthumbnails"/i,
        /"product-textbadges"/i,
        /"product-tile"/i,
        /"product-tile-badges"/i,
        /"product-view"/i,
        /"product-wrapper"/i,
        /"pvproduct"/i,
        /"pvproduct*"/i,
        /"shopcontent"/i,
        /"shop-module"/i,
        /"shopify-section"/i,
        /"shopify-section*"/i,
        /"soldout-image"/i,
        /"add to bag"/i,
        /"add to basket"/i,
        /"add to cart"/i,
        /"shop now"/i,
        "&#36;",
        "€",
        "&#128;",
        "&euro;",
        "£",
        "&#163;",
        "&pound;",
        "¥",
        "&#165;",
        "&yen;",
        "₹",
        "&#x20B9;",
    ];

    var wordTracker = 0;
    for (i = 0; i < shopWords.length; i++) {
        var present = dom.search(shopWords[i]);
        if (present > -1) { // if words are present, add values.
            wordTracker += 1;
        }
    }

    if (wordTracker > 0) { //if there's at least one shopWord present
        chrome.runtime.sendMessage({ msgName: "PageEvaluated", shoppingPage: true }, function(response) {});
    } else {
        chrome.runtime.sendMessage({ msgName: "PageEvaluated", shoppingPage: false }, function(response) {});
    }
};

chrome.runtime.onMessage.addListener(
    function(request) {
        if (request.msgName == "tabSwitched") {
            pageEval();
        }
        return true;
    }
);
