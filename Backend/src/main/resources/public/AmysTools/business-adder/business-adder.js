let environmentSourceIndex = 0;
let animalSourceIndex = 0;
let laborSourceIndex = 0;
let socialSourceIndex = 0;

function start() {
  const originalEnvironmentSource = document.getElementsByClassName("environmentScoreSource")[0];
  const environmentSourceList = document.getElementById("environmentSourceList");
  const originalAnimalSource = document.getElementsByClassName("animalScoreSource")[0];
  const animalSourceList = document.getElementById("animalSourceList");
  const originalLaborSource = document.getElementsByClassName("laborScoreSource")[0];
  const laborSourceList = document.getElementById("laborSourceList");
  const originalSocialSource = document.getElementsByClassName("socialScoreSource")[0];
  const socialSourceList = document.getElementById("socialSourceList");
  console.log(socialSourceList);

  document.getElementById("addEnvironmentSource").addEventListener("click", () => {
    duplicateSource(originalEnvironmentSource);
  });
  document.getElementById("deleteEnvironmentSource").addEventListener("click", () => {
    deleteSource(environmentSourceList);
  });
  document.getElementById("addAnimalSource").addEventListener("click", () => {
    duplicateSource(originalAnimalSource);
  });
  document.getElementById("deleteAnimalSource").addEventListener("click", () => {
    deleteSource(animalSourceList);
  });
  document.getElementById("addLaborSource").addEventListener("click", () => {
    duplicateSource(originalLaborSource);
  });
  document.getElementById("deleteLaborSource").addEventListener("click", () => {
    deleteSource(laborSourceList);
  });
  document.getElementById("addSocialSource").addEventListener("click", () => {
    duplicateSource(originalSocialSource);
  });
  document.getElementById("deleteSocialSource").addEventListener("click", () => {
    console.log(socialSourceList);
    deleteSource(socialSourceList);
  });
}

function duplicateSource(originalSource) {
  let index;
  switch (originalSource.className) {
    case "environmentScoreSource":
      environmentSourceIndex++;
      index = environmentSourceIndex;
      break;
    case "animalScoreSource":
      animalSourceIndex++;
      index = animalSourceIndex;
      break;
    case "laborScoreSource":
      laborSourceIndex++;
      index = laborSourceIndex;
      break;
    case "socialScoreSource":
      socialSourceIndex++;
      index = socialSourceIndex;
      break;
    default:
  }
  const clone = originalSource.cloneNode(true);
  clone.id = originalSource.className + index;
  clone.name = originalSource.className + "[" + index + "]";
  originalSource.parentNode.appendChild(clone);
}

function deleteSource(sourceList) {
  switch (sourceList.id) {
    case "environmentSourceList":
      environmentSourceIndex--;
      break;
    case "animalSourceList":
      animalSourceIndex--;
      break;
    case "laborSourceList":
      laborSourceIndex--;
      break;
    case "socialSourceList":
      socialSourceIndex--;
      break;
    default:
  }
  const lastSource = sourceList.lastChild;
  if (document.getElementsByClassName(lastSource.className).length > 1) {
    lastSource.remove();
  }
}

window.addEventListener("load", start);
