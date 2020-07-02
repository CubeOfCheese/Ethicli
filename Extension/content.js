var isShoppingPage;

window.onload = function pageEval() {
    let dom = document.getElementsByTagName('html')[0].innerHTML;
    var shopWords = [
        /add-to-basket/i,
        /addtocartBtn/i,
        /gl-product-card-container/i,
        /instock/i,
        /in-stock/i,
        /js-detail-product/i,
        /product-block/i,
        /product-block-js/i,
        /product-card/i,
        /product-container/i,
        /product-content/i,
        /product-declinaison/i,
        /product-detail/i,
        /product-detail__buy/i,
        /product-detail__desc/i,
        /product-detail__details/i,
        /product-detail__images/i,
        /product-gallery/i,
        /product-gallery*/i,
        /product-grid__content/i,
        /product-grid__container/i,
        /product-grid__image/i,
        /product-grid__intro/i,
        /product-grid__item/i,
        /product-grid__link/i,
        /product-grid*/i,
        /ProductGrid__Wrapper*/i,
        /product-grid-wider-exp/i,
        /product-image/i,
        /product-image*/i,
        /product-image-container/i,
        /product--image-container/i,
        /product-image-photo/i,
        /product-image-wrapper/i,
        /product-image__link/i,
        /product-images/i,
        /product-images*/i,
        /product-images-container/i,
        /product-info/i,
        /product-info-block/i,
        /productitem/i,
        /productitem_imagewrapper/i,
        /product-item-info/i,
        /product-list/i,
        /product-list*/i,
        /product-listing*/i,
        /product-listing__content/i,
        /product-listing-item__wrapper/i,
        /product-listing__wrapper/i,
        /productlistwrapper/i,
        /product-name/i,
        /product-options/i,
        /product-options*/i,
        /product-options-wrapper/i,
        /product-page/i,
        /product-price/i,
        /product-primary/i,
        /product-primary*/i,
        /product-primary-image/i,
        /product-slider/i,
        /product-slider-container/i,
        /productthumbnail/i,
        /productthumbnails/i,
        /product-textbadges/i,
        /product-tile/i,
        /product-tile-badges/i,
        /product-view/i,
        /product-wrapper/i,
        /pvproduct/i,
        /pvproduct*/i,
        /return-policy/i,
        /return_policy/i,
        /shopcontent/i,
        /shop-module/i,
        /shopify-section/i,
        /shopify-section*/i,
        /"shopping"/i,
        /"shopping*"/i,
        /shopping-bag-empty/i,
        /shopping-window/i,
        /soldout-image/i,
        /woocommerce-products-header__title page-title/i,
        /add to bag/i,
        /add to basket/i,
        /add to cart/i,
        /bought this/i,
        /browse products/i,
        /browse products*/i,
        /buy it now/i,
        /buy this now/i,
        /buy now/i,
        /checkout/i,
        /coupons & deals/i,
        /coupons &amp; deals/i,
        /featured products/i,
        /find a store/i,
        /free shipping/i,
        /get free shipping/i,
        /get free shipping*/i,
        /in stock/i,
        /online order/i,
        /online ordering/i,
        /online orders/i,
        /order cancellation/i,
        /order cancellation policy/i,
        /order now/i,
        /order pickup/i,
        /order policy/i,
        /order policies/i,
        /order status/i,
        /our stores/i,
        /pre-order/i,
        /preorder/i,
        /product catalog/i,
        /refund policy/i,
        /return policy/i,
        /returns & exchanges/i,
        /returns &amp; exchanges/i,
        /returns & refunds/i,
        /returns &amp; refunds/i,
        /returns & repairs/i,
        /returns &amp; repairs/i,
        /same day delivery/i,
        /secure checkout/i,
        /shipping &*/i,
        /shipping &amp;*/i,
        /shipping & returns/i,
        /shipping &amp; returns/i,
        /shipping info/i,
        /shipping information/i,
        /shipping policy/i,
        /shop all/i,
        /shop by */i,
        /shop bestsellers/i,
        /shop by category/i,
        /shop by categories/i,
        /shop by collection/i,
        /shop by collections/i,
        /shop by department/i,
        /shop category/i,
        /shop categories/i,
        /shop collection/i,
        /shop collections/i,
        /shop now/i,
        /shop now*/i,
        /shop our bestsellers/i,
        /shop popular/i,
        /shop popular categories/i,
        /shop product/i,
        /shop product*/i,
        /shop product range/i,
        /shop the collection/i,
        /shopping options/i,
        /ssl secure checkout/i,
        /track order/i,
        /track your order/i,
        /upcoming sales/i
    ];

    var wordTracker = 0;
    for (i = 0; i < shopWords.length; i++) {
        var inArray = shopWords[i].test(dom);
        if (inArray) { // if words are present, add values.
            console.log(shopWords[i]);
            wordTracker += 1;
        }
    }

    if (wordTracker > 0) { //if there's at least one shopWord present
        chrome.runtime.sendMessage({ msgName: "PageEvaluated", shoppingPage: true }, function(response) {});
        isShoppingPage = true;
    } else {
        chrome.runtime.sendMessage({ msgName: "PageEvaluated", shoppingPage: false }, function(response) {});
        isShoppingPage = false;
    }
};

chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
        if (request.msgName == "isShoppingPage?") {
            console.log(isShoppingPage);
            sendResponse({ isShoppingPage: isShoppingPage });
        }
    }
);

chrome.runtime.onMessage.addListener(
    function(request) {
        if (request.msgName == "tabSwitched") {
            pageEval();
        }
        return true;
    }
);
