// let productIndex = 0;
let tagIndex = 0;
let negTagIndex = 0;

function start() {
  // document.getElementById("addProduct").addEventListener("click", duplicateProduct);
  // document.getElementById("deleteProduct").addEventListener("click", deleteProduct);
  const originalPositiveProductTag = document.getElementById("pTag");
  const originalNegativeProductTag = document.getElementById("negPTag");
  const positiveProductTagList = document.getElementById("productTagList");
  const negativeProductTagList = document.getElementById("negativeProductTagList");
  originalNegativeProductTag.style = "display:none";

  document.getElementById("addTag").addEventListener("click", () => {
    duplicateTag(originalPositiveProductTag);
  });
  document.getElementById("deleteTag").addEventListener("click", () => {
    deleteTag(positiveProductTagList);
  });
  document.getElementById("addNegativeTag").addEventListener("click", () => {
    duplicateNegTag(originalNegativeProductTag);
  });
  document.getElementById("deleteNegativeTag").addEventListener("click", () => {
    deleteTag(negativeProductTagList);
  });
  document.getElementById("productImageUrl").addEventListener("change", () => {
    previewImage();
  });
}

// function duplicateProduct() {
//   productIndex++;
//   const originalProduct = document.getElementById("productgroup");
//   const clone = originalProduct.cloneNode(true); // "deep" clone; copies node AND its descendents
//   clone.id = "productgroup" + productIndex; // or clone.id = ""; if the divs don't need an ID
//   originalProduct.parentNode.appendChild(clone);
//   document.getElementById("numproducts").innerText = productIndex + 1;
// }

// function deleteProduct() {

// } // Code to Delete Product


function duplicateTag(originalTag) {
  tagIndex++;
  const clone = originalTag.cloneNode(true);
  clone.id = "pTag" + tagIndex;
  clone.childNodes[1].childNodes[3].name = "productTags[" + tagIndex + "].tag";
  clone.childNodes[3].childNodes[3].name = "productTags[" + tagIndex + "].weight";
  originalTag.parentNode.appendChild(clone);
}

function duplicateNegTag(originalTag) {
  const clone = originalTag.cloneNode(true);
  clone.id = "negPTag" + negTagIndex;
  clone.style = "display:flex";
  clone.required = true;
  clone.childNodes[1].childNodes[3].name = "negativeProductTags[" + negTagIndex + "].tag";
  clone.childNodes[3].childNodes[3].name = "negativeProductTags[" + negTagIndex + "].weight";
  originalTag.parentNode.appendChild(clone);
  negTagIndex++;
}

function deleteTag(productTagList) {
  const lastProductTag = productTagList.lastChild;
  if (document.getElementsByClassName(lastProductTag.className).length > 1) {
    lastProductTag.remove();
  }
}

function previewImage() {
  document.getElementById("productUrlPreview").src = document.getElementById("productImageUrl").value;
}

window.addEventListener("load", start);
