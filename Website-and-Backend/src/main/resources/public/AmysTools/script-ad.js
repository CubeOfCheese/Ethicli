let productIndex = 0;
let tagIndex = 0;

function start() {
  console.log("window onload running");

  // document.getElementById("addproduct").addEventListener("click", duplicateProduct);
  console.log(document.getElementById("addtag"));
  document.getElementById("addtag").addEventListener("click", duplicateTag);
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
  clone.childNodes[3].name = "productTags[" + tagIndex + "].tag";
  clone.childNodes[7].name = "productTags[" + tagIndex + "].weight";
  originalTag.parentNode.appendChild(clone);
}

console.log("I'm running");

window.addEventListener("load", start);
