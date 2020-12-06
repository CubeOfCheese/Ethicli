let productIndex = 0;
let tagIndex = 0;

function start() {
  // document.getElementById("addproduct").addEventListener("click", duplicateProduct);
  // document.getElementById("deleteproduct").addEventListener("click", deleteProduct);
  document.getElementById("addtag").addEventListener("click", duplicateTag);
  document.getElementById("deletetag").addEventListener("click", deleteTag);
  document.getElementById("productimageurl").addEventListener("change", previewImage);
}

function duplicateProduct() {
  productIndex++;
  const originalProduct = document.getElementById("productgroup");
  const clone = originalProduct.cloneNode(true); // "deep" clone; copies node AND its descendents
  clone.id = "productgroup" + productIndex; // or clone.id = ""; if the divs don't need an ID
  originalProduct.parentNode.appendChild(clone);
  document.getElementById("numproducts").innerText = productIndex + 1;
}

function duplicateTag() {
  tagIndex++;
  console.log("duplicateTag called");
  const originalTag = document.getElementById("ptags");
  const clone = originalTag.cloneNode(true);
  clone.id = "ptags" + tagIndex;
  console.log(clone.childNodes);
  console.log();
  clone.childNodes[1].childNodes[3].name = "productTags[" + tagIndex + "].tag";
  clone.childNodes[3].childNodes[3].name = "productTags[" + tagIndex + "].weight";
  originalTag.parentNode.appendChild(clone);
}

function previewImage() {
  document.getElementById("productUrlPreview").src = document.getElementById("productimageurl").value;
}

function deleteProduct() {

} // Code to Delete Product

function deleteTag() {
  const posProductTagList = document.getElementById("productTagList");
  const lastProductTag = posProductTagList.lastChild;
  lastProductTag.remove();
}

console.log("I'm running");

window.addEventListener("load", start);
