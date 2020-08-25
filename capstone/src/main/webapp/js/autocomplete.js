/*
 * Largely inspired by JS code from W3Schools: https://www.w3schools.com/howto/howto_js_autocomplete.asp
 */
const SEARCH_INPUT = document.getElementById('search');
const ITEM_CLASS = 'autocomplete-items';
const ACTIVE_CLASS = 'autocomplete-active';
const LIST_ID = 'autocomplete-list';
const UP_KEY = 40;
const DOWN_KEY = 38;
const ENTER = 13;
let selectedElmntPos = -1;

/**
 * Adds the 'active' tag to an autocomplete elmt
 * @param {Object} autocompleteListElmt
 * @return {bool}
 */
function addActiveTag(autocompleteListElmt) {
  if (!autocompleteListElmt) {
    return false;
  }
  removeActiveTag(autocompleteListElmt);
  if (selectedElmntPos >= autocompleteListElmt.length) {
    selectedElmntPos = 0;
  } else if (selectedElmntPos < 0) {
    selectedElmntPos = (autocompleteListElmt.length - 1);
  }
  autocompleteListElmt[selectedElmntPos].classList.add(ACTIVE_CLASS);
}

/**
 * Removes the 'active' tag from an autocomplete elmt
 * @param {Object} autocompleteListElmt
 */
function removeActiveTag(autocompleteListElmt) {
  for (elmt of autocompleteListElmt) {
    elmt.classList.remove(ACTIVE_CLASS);
  }
}

/**
 * Closes dropdown autocomplete list
 * Does not close the element provided
 * @param {Object} elmnt
 * @return {void}
 */
function closeAllElmntExcept(elmnt) {
  const autoItems = document.getElementsByClassName(ITEM_CLASS);
  for (const item of autoItems) {
    if (item != elmnt && item != SEARCH_INPUT) {
      item.parentNode.removeChild(item);
    }
  }
}

/**
 * Adds valid suggestions to list of stored suggestions
 * @param{string} validSuggestion
 */
function keepTrackOfSuggestions(validSuggestion) {
  const parent = document.getElementById('suggestions');
  const listElt = document.createElement('li');
  listElt.innerHTML = validSuggestion;
  parent.appendChild(listElt);
}

/**
 * Current placeholder until servlet is created
 * @return{Array<string>} arr
 */
function getListOfSuggestions() {
  const arr = ['Hallo', 'Hello', 'Hi', 'Hiya', 'Howdy', 'Wassup'];
  return arr;
}

/**
 * Identifies and returns relevant suggestions in the arr
 * @param {string[]} arr
 * @param {string} val
 * @return {string[]} trimArr
 */
function getRelevantDataSuggestions(arr, val) {
  const trimArr = [];
  val = val.toUpperCase();
  for (const arrElt of arr) {
    if (arrElt.substr(0, val.length).toUpperCase() == val.toUpperCase()) {
      trimArr.push(arrElt);
    }
  }
  return trimArr;
}

/**
 * Takes relevant suggestions and displays them in DOM
 * @param {Object} trimArr
 * @param {Object} autocompleteList
 * @param {string} val
 * @return {void}
 */
function displaySuggestions(trimArr, autocompleteList, val) {
  for (arrElt of trimArr) {
    const listElmt = document.createElement('DIV');
    listElmt.innerHTML = '<strong>' +
                         arrElt.substr(0, val.length) +
                         '</strong>';
    listElmt.innerHTML += arrElt.substr(val.length);
    const cpyArrElt = arrElt;
    listElmt.addEventListener('click', (e) => {
      SEARCH_INPUT.value = cpyArrElt;
      keepTrackOfSuggestions(cpyArrElt);
      closeAllElmntExcept();
    });
    autocompleteList.appendChild(listElmt);
  }
}

/**
 * Event occurrance when input is provided to autocomplete field
 * @return {bool}
 */
function giveSuggestions() {
  closeAllElmntExcept();
  const val = SEARCH_INPUT.value;
  if (!val) {
    return false;
  }
  const arr = getListOfSuggestions();
  selectedElmntPos = -1;
  const autocompleteList = document.createElement('DIV');
  autocompleteList.setAttribute('id', LIST_ID);
  autocompleteList.setAttribute('class', ITEM_CLASS);
  SEARCH_INPUT.parentNode.appendChild(autocompleteList);
  const trimArr = getRelevantDataSuggestions(arr, val);
  displaySuggestions(trimArr, autocompleteList, val);
  return true;
}

/**
 * Event occurance when arrow keys are pressed
 * @param {keypress} e
 * @return {void}
 */
function keyDown(e) {
  let listElmt = document.getElementById(LIST_ID);
  if (listElmt) {
    listElmt = listElmt.getElementsByTagName('div');
  }
  if (e.keyCode == UP_KEY) {
    selectedElmntPos++;
    addActiveTag(listElmt);
  } else if (e.keyCode == DOWN_KEY) {
    selectedElmntPos--;
    addActiveTag(listElmt);
  } else if (e.keyCode == ENTER) {
    e.preventDefault();
    if (selectedElmntPos > -1) {
      if (listElmt) {
        listElmt[selectedElmntPos].click();
      }
    }
  }
}

/* Event occurance when mouse is clicked */
document.addEventListener('click', (e) => {
  closeAllElmntExcept(e.target, SEARCH_INPUT);
});
