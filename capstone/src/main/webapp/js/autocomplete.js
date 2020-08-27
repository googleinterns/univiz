/* eslint-disable no-unused-vars */

/*
 * Largely inspired by JS code from W3Schools: https://www.w3schools.com/howto/howto_js_autocomplete.asp
 */
const SEARCH_INPUT = document.getElementById('search');
const ITEM_CLASS = 'autocomplete-items';
const ACTIVE_CLASS = 'autocomplete-active';
const LIST_ID = 'autocomplete-list';
const UP_KEY = 'ArrowUp';
const DOWN_KEY = 'ArrowDown';
const ENTER = 'Enter';
let selectedSuggestionPosition = -1;

/**
 * Adds the 'active' tag to an autocomplete elmt
 * @param {HTMLDivElement} autocompleteListElmt
 */
function addActiveTag(autocompleteListElmt) {
  if (!autocompleteListElmt) {
    return;
  }
  removeActiveTag(autocompleteListElmt);
  if (selectedSuggestionPosition >= autocompleteListElmt.length) {
    selectedSuggestionPosition = 0;
  } else if (selectedSuggestionPosition < 0) {
    selectedSuggestionPosition = (autocompleteListElmt.length - 1);
  }
  autocompleteListElmt[selectedSuggestionPosition].classList.add(ACTIVE_CLASS);
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
 * @return {string[]} relevantSuggestions
 */
function getRelevantDataSuggestions(arr, val) {
  const relevantSuggestions = [];
  val = val.toUpperCase();
  for (const arrElt of arr) {
    if (arrElt.substr(0, val.length).toUpperCase() === val.toUpperCase()) {
      relevantSuggestions.push(arrElt);
    }
  }
  return relevantSuggestions;
}

/**
 * Takes relevant suggestions and displays them in DOM
 * @param {Object} relevantSuggestions
 * @param {Object} autocompleteList
 * @param {string} val
 */
function displaySuggestions(relevantSuggestions, autocompleteList, val) {
  for (arrElt of relevantSuggestions) {
    const listElmt = document.createElement('div');
    listElmt.innerHTML = '<strong>' +
                         arrElt.substr(0, val.length) +
                         '</strong>';
    listElmt.innerHTML += arrElt.substr(val.length);
    const cpyArrElt = arrElt;
    listElmt.addEventListener('click', () => {
      SEARCH_INPUT.value = cpyArrElt;
      keepTrackOfSuggestions(cpyArrElt);
      closeAllElmntExcept();
    });
    autocompleteList.appendChild(listElmt);
  }
}

/**
 * Event occurrance when input is provided to autocomplete field
 */
function giveSuggestions() {
  closeAllElmntExcept();
  const val = SEARCH_INPUT.value;
  if (!val) {
    return;
  }
  const arr = getListOfSuggestions();
  selectedSuggestionPosition = -1;
  const autocompleteList = document.createElement('div');
  autocompleteList.setAttribute('id', LIST_ID);
  autocompleteList.setAttribute('class', ITEM_CLASS);
  SEARCH_INPUT.parentNode.appendChild(autocompleteList);
  const relevantSuggestions = getRelevantDataSuggestions(arr, val);
  displaySuggestions(relevantSuggestions, autocompleteList, val);
}

/**
 * Event occurance when arrow keys are pressed
 * @param {keypress} e
 */
function keyDown(e) {
  let listElmt = document.getElementById(LIST_ID);
  if (listElmt) {
    listElmt = listElmt.getElementsByTagName('div');
  } else {
    return;
  }
  if (e.code === DOWN_KEY) {
    selectedSuggestionPosition++;
    addActiveTag(listElmt);
  } else if (e.code === UP_KEY) {
    selectedSuggestionPosition--;
    addActiveTag(listElmt);
  } else if (e.code === ENTER) {
    e.preventDefault();
    if (selectedSuggestionPosition > -1) {
      if (listElmt) {
        listElmt[selectedSuggestionPosition].click();
      }
    }
  }
}

/* Event occurance when mouse is clicked */
document.addEventListener('click', (e) => {
  closeAllElmntExcept(e.target, SEARCH_INPUT);
  SEARCH_INPUT.value = '';
});
