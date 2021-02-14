window.onload = () => {
  let i = 0;
  let j = 0;
  const originalProduct = document.getElementById("productgroup");
  const originalTag = document.getElementById("ptags");

  document.getElementById("addproduct").addEventListener("click", duplicateProduct);
  document.getElementById("addtag").addEventListener("click", duplicateTag);

  function duplicateProduct() {
    const clone = originalProduct.cloneNode(true); // "deep" clone; copies node AND its descendents
    clone.id = "productgroup" + ++i; // or clone.id = ""; if the divs don't need an ID
    originalProduct.parentNode.appendChild(clone);
    document.getElementById("numproducts").innerText = i + 1;
  }

  function duplicateTag() {
    const newtag = "document.getElementById('ptags'):nth-child(" + i + ")";
    const clone = originalTag.cloneNode(true);
    clone.id = "ptags" + ++j;
    originalTag.parentNode.appendChild(clone);
  }
};
