const searchInput = document.getElementById('search');
let selectedSuggElt = -1;
const itemClass = 'autocomplete-items';
const activeClass = 'autocomplete-active';
const listId = 'autocomplete-list';
/**
 * @param {Object} autocompleteListElmt
 * @return {bool}
 * Adds the 'active' tag to an autocomplete elmt
 */
function addActiveTag(autocompleteListElmt) {
  if (!autocompleteListElmt) {
    return false;
  }
  removeActiveTag(autocompleteListElmt);
  if (selectedSuggElt >= autocompleteListElmt.length) {
    selectedSuggElt = 0;
  } else if (selectedSuggElt < 0) {
    selectedSuggElt = (autocompleteListElmt.length - 1);
  }
  autocompleteListElmt[selectedSuggElt].classList.add(activeClass);
}

/**
 * @param {Object} autocompleteListElmt
 * @return {void}
 * Removes the 'active' tag from an autocomplete elmt
 */
function removeActiveTag(autocompleteListElmt) {
  for (elmt of autocompleteListElmt) {
    elmt.classList.remove(activeClass);
  }
  //autocompleteListElmt.forEach((element) => element.classList.remove(activeClass));
}

/**
 * @param {Object} elmnt
 * @param {Object} inp
 * @return {void}
 * Closes dropdown autocomplete list
 */
function closeAllLists(elmnt) {
  const autoItems = document.getElementsByClassName(itemClass);
  for (const item of autoItems) {
    if (item != elmnt && item != searchInput) {
      item.parentNode.removeChild(item);
    }
  }
 // autoItems.filter((item) => (item != elmnt && item != searchInput))
   //        .forEach((item) => item.parentNode.removeChild(item));
}

/**
 * @return{Object} arr
 * Current placeholder until servlet is created
 */
function getListOfSuggestions() {
  const arr = ['Hello', 'Hi', 'Howdy'];
  return arr;
}

/**
 * @param {Object} arr
 * @param {string} val
 * @return {Object} trimArr
 * Identifies and returns relevant suggestions in the arr
 */
function getRelevantDataSuggestions(arr, val) {
  const trimArr = [];
  for (const arrElt of arr) {
    if (arrElt.substr(0, val.length).toUpperCase() == val.toUpperCase()) {
      trimArr.push(arrElt);
    }
  }
  return trimArr;
}

/**
 * @param {Object} trimArr
 * @param {Object} autocompleteList
 * @return {void}
 * Takes relevant suggestions and displays them in DOM
 */
function displaySuggestions(trimArr, autocompleteList, val) {
  for (arrElt of trimArr) {
    if (true) {
      const listElmt = document.createElement('DIV');
      listElmt.innerHTML = '<strong>' +
                           arrElt.substr(0, val.length) +
                           '</strong>';
      listElmt.innerHTML += arrElt.substr(val.length);
      listElmt.innerHTML += '<input type=\'hidden\' value=\'arrElt\'>';
      listElmt.addEventListener('click', function(e) {
        searchInput.value = searchInput.getElementsByTagName('input')[0].value;
        closeAllLists();
      });
      autocompleteList.appendChild(listElmt);
    }
  }
}

/* TODO: JSDoc
 * Event occurrance when input is provided to autocomplete field */
function giveSuggestions() {
  closeAllLists();
  const val = searchInput.value;
  if (!val) {
    return false;
  }
  const arr = getListOfSuggestions();
  selectedSuggElt = -1;
  const autocompleteList = document.createElement('div');
  autocompleteList.setAttribute('id', searchInput.id + listId);
  autocompleteList.setAttribute('class', itemClass);
  searchInput.parentNode.appendChild(autocompleteList);
  const trimArr = getRelevantDataSuggestions(arr, val);
  displaySuggestions(trimArr, autocompleteList, val);
}

/* Event occurance when arrow keys are pressed */
function keyDown(e) {
  console.log("Key down!");
  let listElmt = document.getElementById(searchInput.id + listId);
  if (listElmt) {
    listElmt = listElmt.getElementsByTagName('div');
  }
  if (e.keyCode == 40) {/* Up key */
    selectedSuggElt++;
    addActiveTag(listElmt);
  } else if (e.keyCode == 38) {/* Down key */
    selectedSuggElt--;
    addActiveTag(listElmt);
  } else if (e.keyCode == 13) {/* Enter key */
    e.preventDefault();
    if (selectedSuggElt > -1) {
      if (listElmt) {
        listElmt[selectedSuggElt].click();
      }
    }
  }
}

/* Event occurance when mouse is clicked */
document.addEventListener('click', function(e) {
  closeAllLists(e.target, searchInput);
});
