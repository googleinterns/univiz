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
let selectedElmntPos = -1;

/**
 * Gets suggestions to display to the users
 * @return{void}
 */
function getSuggestions() {
  const fetchStr = '/search?query=' + SEARCH_INPUT.value;
  console.log('FetchStr: ', fetchStr);
  fetch(fetchStr).then((response) => response.json()).then((suggestions) => {
    console.log('Here! ', suggestions);
    giveSuggestions(suggestions);
  });
}

/**
 * Sends final college list to the dashboard
 * @return{void}
 */
function sendCollegeInformation() {
  const listItems = document.querySelector('ul').children;
  const listArray = Array.from(listItems);
  let urlHtml = 'dashboard.html?id=';
  for (const elt of listArray) {
    urlHtml += elt.id;
    urlHtml += ',';
  }
  window.location.href = urlHtml;
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
  listElt.setAttribute('id', validSuggestion.collegeId.id);
  listElt.innerHTML = validSuggestion.collegeName;
  parent.appendChild(listElt);
}

/**
 * Takes relevant suggestions and displays them in DOM
 * @param {Object} trimArr
 * @param {Object} autocompleteList
 * @param {string} val
 */
function displaySuggestions(trimArr, autocompleteList, val) {
  for (arrElt of trimArr) {
    const listElmt = document.createElement('div');
    listElmt.innerHTML = '<strong>' +
                         arrElt.collegeName.substr(0, val.length) +
                         '</strong>';
    listElmt.innerHTML += arrElt.collegeName.substr(val.length);
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
 * @param{Object} suggestions
 */
function giveSuggestions(suggestions) {
  closeAllElmntExcept();
  const val = SEARCH_INPUT.value;
  if (!val) {
    return;
  }
  if (SEARCH_INPUT.value !== val) {
    return;
  }
  selectedElmntPos = -1;
  const autocompleteList = document.createElement('div');
  autocompleteList.setAttribute('id', LIST_ID);
  autocompleteList.setAttribute('class', ITEM_CLASS);
  SEARCH_INPUT.parentNode.appendChild(autocompleteList);
  displaySuggestions(suggestions, autocompleteList, val);
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
    selectedElmntPos++;
    addActiveTag(listElmt);
  } else if (e.code === UP_KEY) {
    selectedElmntPos--;
    addActiveTag(listElmt);
  } else if (e.code === ENTER) {
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
  SEARCH_INPUT.value = '';
});
