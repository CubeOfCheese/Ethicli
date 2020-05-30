var shopStatus;

window.onload = function() {
    console.log("helloo");
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
            console.log("shop");
            shopStatus = true;
        } else {
            console.log("not shop");
            shopStatus = false;
        }
        console.log(request.shoppingPage);
    }
);
console.log("popup.js");