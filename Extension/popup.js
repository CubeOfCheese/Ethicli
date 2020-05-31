chrome.runtime.sendMessage({ msgName: "isShoppingPage?" }, function(response) {
  if (response.shoppingPage == true) {
    document.getElementById("shopping").classList.remove("notShop");
    document.getElementById("notShopMessage").classList.remove("notShop");
  }
  else {
    document.getElementById("shopping").classList.add("notShop");
    document.getElementById("notShopMessage").classList.add("notShop");
    document.body.style.height = "150px";
  }
});
