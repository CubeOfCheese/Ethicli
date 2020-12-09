let productIndex = 0;
let tagIndex = 0;

function start() {
  // document.getElementById("addProduct").addEventListener("click", duplicateProduct);
  // document.getElementById("deleteProduct").addEventListener("click", deleteProduct);
  const originalPositiveProductTag = document.getElementById("pTag");
  const originalNegativeProductTag = document.getElementById("negPTag");
  const positiveProductTagList = document.getElementById("productTagList");
  const negativeProductTagList = document.getElementById("negativeProductTagList");
  document.getElementById("addTag").addEventListener("click", duplicateTag(originalPositiveProductTag));
  document.getElementById("deleteTag").addEventListener("click", deleteTag(positiveProductTagList));
  document.getElementById("addNegativeTag").addEventListener("click", duplicateTag(originalNegativeProductTag));
  document.getElementById("deleteNegativeTag").addEventListener("click", deleteTag(negativeProductTagList));
  document.getElementById("productImageUrl").addEventListener("change", previewImage);
}

function duplicateProduct() {
  productIndex++;
  const originalProduct = document.getElementById("productgroup");
  const clone = originalProduct.cloneNode(true); // "deep" clone; copies node AND its descendents
  clone.id = "productgroup" + productIndex; // or clone.id = ""; if the divs don't need an ID
  originalProduct.parentNode.appendChild(clone);
  document.getElementById("numproducts").innerText = productIndex + 1;
}

function deleteProduct() {

} // Code to Delete Product


function duplicateTag(originalTag) {
  tagIndex++;
  console.log("duplicateTag called");
  const clone = originalTag.cloneNode(true);
  clone.id = "ptags" + tagIndex;
  console.log(clone.childNodes);
  console.log();
  clone.childNodes[1].childNodes[3].name = "productTags[" + tagIndex + "].tag";
  clone.childNodes[3].childNodes[3].name = "productTags[" + tagIndex + "].weight";
  originalTag.parentNode.appendChild(clone);
}

function deleteTag(productTagList) {
  const lastProductTag = productTagList.lastChild;
  lastProductTag.remove();
}


function previewImage() {
  document.getElementById("productUrlPreview").src = document.getElementById("productImageUrl").value;
}

console.log("I'm running");

window.addEventListener("load", start);
