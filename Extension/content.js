window.onload = function() {
    var dom = document.getElementsByTagName('html')[0].innerHTML;
    var present = dom.search("content=\"product\"")
    if (present == -1) {
        present = dom.search("gl-product-card-container")
    }
    if (present != -1) {
        chrome.runtime.sendMessage({ shoppingPage: true }, function(response) {});
        document.getElementById("shopping").classList.remove("notShop");
        document.getElementById("notShopMessage").classList.remove("notShop");
    } else {
        chrome.runtime.sendMessage({ shoppingPage: false }, function(response) {});
        document.getElementById("shopping").classList.add("notShop");
        document.getElementById("notShopMessage").classList.add("notShop");
        document.body.style.height = "150px";
    }
};