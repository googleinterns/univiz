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
 * Gets suggestions to display to the users
 */
function suggestionInput() {
  const value = SEARCH_INPUT.value.trim();
  if (!value) {
    return;
  }
  const fetchStr = '/search?query=' + value;
  fetch(fetchStr).then((response) => response.json()).then((suggestions) => {
    getAllProposedSuggestions(suggestions, value);
  });
}

/**
 * Sends final college list to the dashboard
 */
function sendCollegeInformationToDashboard() {
  const listItems = document.getElementById('suggestions').children;
  const stringOfIds = Array.from(listItems).map((l) => l.id).join(',');
  const dashboardUrl = 'dashboard.html?id=' + stringOfIds;
  window.location.href = dashboardUrl;
}

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
  autocompleteListElement[selectedSuggestionPosition]
      .classList.add(ACTIVE_CLASS);
}

/**
 * Removes the 'active' tag from an autocomplete elmt
 * @param {HTMLDivElement} autocompleteListElement
 */
function removeActiveTag(autocompleteListElement) {
  for (element of autocompleteListElement) {
    element.classList.remove(ACTIVE_CLASS);
  }
}

/**
 * Closes dropdown autocomplete list
 * Does not close the element provided
 * @param {HTMLDivElement} element
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
 * Creates List Element to display a college name suggestion
 * @param {string} collegeName
 * @param {string} value
 * @return {HTMLDivElement} listElmt
 */
function createAutocompleteListElement(collegeName, value) {
  const listElement = document.createElement('div');
  let listElementInnerHTML = collegeName;
  const partsOfTheCollegeName = value.split(' ');
  for (partialName of partsOfTheCollegeName) {
    const listElementInnerHTMLUpper = listElementInnerHTML.toUpperCase();
    const valueIndex =
      listElementInnerHTMLUpper.indexOf(partialName.toUpperCase());
    if (valueIndex < 0) {
      continue;
    }
    let updatedCollegeName = '';
    updatedCollegeName = listElementInnerHTML.substr(0, valueIndex);
    updatedCollegeName += '<strong>' +
                          listElementInnerHTML.substr(
                              valueIndex, partialName.length) +
                          '</strong>';
    updatedCollegeName += listElementInnerHTML.substr(
        partialName.length + valueIndex);
    listElementInnerHTML = updatedCollegeName;
  }
  listElement.innerHTML = listElementInnerHTML;
  return listElement;
}

/**
 * Adds valid suggestions to list of stored suggestions
 * @param {string} validSuggestion
 */
function keepTrackOfChosenColleges(validSuggestion) {
  if (document.getElementById(validSuggestion.collegeId.id)) {
    return;
  }
  const parent = document.getElementById('suggestions');
  const listElement = document.createElement('li');
  listElement.setAttribute('id', validSuggestion.collegeId.id);
  listElement.innerHTML = validSuggestion.collegeName;
  parent.appendChild(listElement);
}

/**
 * Takes relevant suggestions and displays them in DOM
 * @param {Array<string>} suggestions
 * @param {HTMLDivElement} autocompleteList
 * @param {string} value
 */
function displayCollegeSuggestions(suggestions, autocompleteList, value) {
  for (arrayElement of suggestions) {
    const listElement =
      createAutocompleteListElement(arrayElement.collegeName, value);
    const copyArrayElement = arrayElement;
    listElement.addEventListener('click', () => {
      SEARCH_INPUT.value = copyArrayElement;
      keepTrackOfChosenColleges(copyArrayElement);
      closeAllElements();
    });
    autocompleteList.appendChild(listElement);
  }
}

/**
 * Event occurrance when input is provided to autocomplete field
 * @param {Array<string, int>} suggestions
 * @param {string} val
 */
function getAllProposedSuggestions(suggestions, val) {
  closeAllElements();
  selectedSuggestionPosition = -1;
  const autocompleteList = document.createElement('div');
  autocompleteList.setAttribute('id', SUGGESTION_LIST_ID);
  autocompleteList.setAttribute('class', ITEM_CLASS);
  SEARCH_INPUT.parentNode.appendChild(autocompleteList);
  displayCollegeSuggestions(suggestions, autocompleteList, val);
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
