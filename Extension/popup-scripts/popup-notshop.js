// for popupNotShop.html
window.addEventListener("load", () => {
  document.getElementById("notShopBeforeRequestMessage").onclick = () => {
    document.getElementById("notShopMessage").classList.add("requestedShop");
    // Requested Shop Here
  };
});

// Opened-NotShop analytics event
