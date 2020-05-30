var shopStatus;

window.onload = function() {
    if (shopStatus) {
        document.getElementById("shopping").classList.remove("notShop");
        document.getElementById("notShopMessage").classList.remove("notShop");
    } else {
        document.getElementById("shopping").classList.add("notShop");
        document.getElementById("notShopMessage").classList.add("notShop");
        document.body.style.height = "150px";
    }
}

chrome.runtime.onMessage.addListener(
    function(request, sender, sendResponse) {
        if (request.shoppingPage === true) {
            shopStatus = true;
        } else {
            shopStatus = false;
        }
    }
);
