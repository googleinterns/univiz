/* eslint-disable no-unused-vars */

/*
 * Largely inspired by JS code from W3Schools: https://www.w3schools.com/howto/howto_js_autocomplete.asp
 */
const SEARCH_INPUT = document.getElementById('search');
const ITEM_CLASS = 'autocomplete-items';
const ACTIVE_CLASS = 'autocomplete-active';
const SUGGESTION_LIST_ID = 'autocomplete-list';
const UP_KEY = 'ArrowUp';
const DOWN_KEY = 'ArrowDown';
const ENTER_KEY = 'Enter';
let selectedSuggestionPosition = -1;

/**
 * Adds the 'active' tag to an autocomplete elmt
 * @param {HTMLDivElement} autocompleteListElement
 */
function addActiveTag(autocompleteListElement) {
  if (!autocompleteListElement) {
    return;
  }
  removeActiveTag(autocompleteListElement);
  if (selectedSuggestionPosition >= autocompleteListElement.length) {
    selectedSuggestionPosition = 0;
  } else if (selectedSuggestionPosition < 0) {
    selectedSuggestionPosition = (autocompleteListElement.length - 1);
  }
  autocompleteListElement[selectedSuggestionPosition].classList.add(ACTIVE_CLASS);
}

/**
 * Removes the 'active' tag from an autocomplete elmt
 * @param {HTMLDivElement} autocompleteListElmt
 */
function removeActiveTag(autocompleteListElement) {
  for (element of autocompleteListElement) {
    element.classList.remove(ACTIVE_CLASS);
  }
}

/**
 * Closes dropdown autocomplete list
 * Does not close the element provided
 * @param {HTMLDivElement} elmnt
 */
function closeAllElementExcept(element) {
  const autoItems = document.getElementsByClassName(ITEM_CLASS);
  for (const item of autoItems) {
    if (item != element) {
      item.parentNode.removeChild(item);
    }
  }
}

/**
 * Closes entire dropdown autocomplete list
 */
function closeAllElements() {
  const autoItems = document.getElementsByClassName(ITEM_CLASS);
  for (const item of autoItems) {
    item.parentNode.removeChild(item);
  }
}

/**
 * Adds valid suggestions to list of stored suggestions
 * @param {string} validSuggestion
 */
function keepTrackOfSuggestions(validSuggestion) {
  const parent = document.getElementById('suggestions');
  const listElement = document.createElement('li');
  listElement.innerHTML = validSuggestion;
  parent.appendChild(listElement);
}

/**
 * Current placeholder until servlet is created
 * @return {Array<string>} arr
 */
function getListOfSuggestions() {
  const array= ['Hallo', 'Hello', 'Hi', 'Hiya', 'Howdy', 'Wassup'];
  return array;
}

/**
 * Identifies and returns relevant suggestions in the arr
 * @param {Array<string>} arr
 * @param {string} val
 * @return {Array<string>} relevantSuggestions
 */
function getRelevantDataSuggestions(array, value) {
  const relevantSuggestions = [];
  value = value.toUpperCase();
  for (const arrayElement of array) {
    if (arrayElement.toUpperCase().startsWith(value.toUpperCase())) {
      relevantSuggestions.push(arrayElement);
    }
  }
  return relevantSuggestions;
}

/**
 * Takes relevant suggestions and displays them in DOM
 * @param {Array<string>} relevantSuggestions
 * @param {HTMLDivElement} autocompleteList
 * @param {string} val
 */
function displaySuggestions(relevantSuggestions, autocompleteList, value) {
  for (arrayElement of relevantSuggestions) {
    const listElement = document.createElement('div');
    listElement.innerHTML = '<strong>' +
                         arrayElement.substr(0, value.length) +
                         '</strong>';
    listElement.innerHTML += arrayElement.substr(value.length);
    const copyArrayElement = arrayElement;
    listElement.addEventListener('click', () => {
      SEARCH_INPUT.value = copyArrayElement;
      keepTrackOfSuggestions(copyArrayElement);
      closeAllElements();
    });
    autocompleteList.appendChild(listElement);
  }
}

/**
 * Event occurrance when input is provided to autocomplete field
 */
function getAllProposedSuggestions() {
  closeAllElements();
  const value = SEARCH_INPUT.value;
  if (!value) {
    return;
  }
  const array = getListOfSuggestions();
  selectedSuggestionPosition = -1;
  const autocompleteList = document.createElement('div');
  autocompleteList.setAttribute('id', SUGGESTION_LIST_ID);
  autocompleteList.setAttribute('class', ITEM_CLASS);
  SEARCH_INPUT.parentNode.appendChild(autocompleteList);
  const relevantSuggestions = getRelevantDataSuggestions(array, value);
  displaySuggestions(relevantSuggestions, autocompleteList, value);
}

/**
 * Event occurance when arrow keys are pressed
 * @param {keypress} e
 */
function keyDown(e) {
  let listElement = document.getElementById(SUGGESTION_LIST_ID);
  if (listElement) {
    listElement = listElement.getElementsByTagName('div');
  } else {
    return;
  }
  if (e.code === DOWN_KEY) {
    selectedSuggestionPosition++;
    addActiveTag(listElement);
  } else if (e.code === UP_KEY) {
    selectedSuggestionPosition--;
    addActiveTag(listElement);
  } else if (e.code === ENTER_KEY) {
    e.preventDefault();
    if (selectedSuggestionPosition > -1) {
      if (listElement) {
        listElement[selectedSuggestionPosition].click();
      }
    }
  }
}

/* Event occurance when mouse is clicked */
document.addEventListener('click', (e) => {
  closeAllElementExcept(e.target, SEARCH_INPUT);
  SEARCH_INPUT.value = '';
});
