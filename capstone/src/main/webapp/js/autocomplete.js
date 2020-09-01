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
  const val = SEARCH_INPUT.value.trim();
  if (!val) {
    return;
  }
  const fetchStr = '/search?query=' + val;
  fetch(fetchStr).then((response) => response.json()).then((suggestions) => {
    getsAllProposedSuggestions(suggestions, val);
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
 * @param {HTMLDivElement} autocompleteListElmt
 */
function removeActiveTag(autocompleteListElmt) {
  for (elmt of autocompleteListElmt) {
    elmt.classList.remove(ACTIVE_CLASS);
  }
}

/**
 * Closes dropdown autocomplete list
 * Does not close the element provided
 * @param {HTMLDivElement} elmnt
 */
function closeAllElmntExcept(elmnt) {
  const autoItems = document.getElementsByClassName(ITEM_CLASS);
  for (const item of autoItems) {
    if (item != elmnt) {
      item.parentNode.removeChild(item);
    }
  }
}

/**
 * Adds valid suggestions to list of stored suggestions
 * @param {string} validSuggestion
 */
function keepTrackOfChosenColleges(validSuggestion) {
  const parent = document.getElementById('suggestions');
  const listElt = document.createElement('li');
  listElt.setAttribute('id', validSuggestion.collegeId.id);
  listElt.innerHTML = validSuggestion.collegeName;
  parent.appendChild(listElt);
}

/**
 * Creates List Element to display a college name suggestion
 * @param {string} collegeName
 * @param {string} val
 * @return {HTMLDivElement} listElmt
 */
function createAutocompleteListElement(collegeName, val) {
  const listElmt = document.createElement('div');
  const collegeNameUpper = collegeName.toUpperCase();
  const valIndex = collegeNameUpper.indexOf(val.toUpperCase());
  listElmt.innerHTML = collegeName.substr(0, valIndex);
  listElmt.innerHTML += '<strong>' +
                       collegeName.substr(valIndex, val.length) +
                       '</strong>';
  listElmt.innerHTML += collegeName.substr(val.length + valIndex);
  return listElmt;
}

/**
 * Takes relevant suggestions and displays them in DOM
 * @param {Array<string, int>} relevantSuggestions
 * @param {HTMLDivElement} autocompleteList
 * @param {string} val
 */
function displayCollegeSuggestions(relevantSuggestions, autocompleteList, val) {
  for (arrElt of relevantSuggestions) {
    const listElmt = createAutocompleteListElement(arrElt.collegeName, val);
    const cpyArrElt = arrElt;
    listElmt.addEventListener('click', () => {
      SEARCH_INPUT.value = cpyArrElt;
      keepTrackOfChosenColleges(cpyArrElt);
      closeAllElmntExcept();
    });
    autocompleteList.appendChild(listElmt);
  }
}

/**
 * Event occurrance when input is provided to autocomplete field
 * @param {Array<string, int>} suggestions
 * @param {string} val
 */
function getsAllProposedSuggestions(suggestions, val) {
  closeAllElmntExcept();
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
  let listElmt = document.getElementById(SUGGESTION_LIST_ID);
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
  } else if (e.code === ENTER_KEY) {
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
