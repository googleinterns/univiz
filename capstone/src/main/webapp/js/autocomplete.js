const SEARCH_INPUT = document.getElementById('search');
const ITEM_CLASS = 'autocomplete-items';
const ACTIVE_CLASS = 'autocomplete-active';
const LIST_ID = 'autocomplete-list';
let SELECTED_SUGG_ELT = -1;

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
  if (SELECTED_SUGG_ELT >= autocompleteListElmt.length) {
    SELECTED_SUGG_ELT = 0;
  } else if (SELECTED_SUGG_ELT < 0) {
    SELECTED_SUGG_ELT = (autocompleteListElmt.length - 1);
  }
  autocompleteListElmt[SELECTED_SUGG_ELT].classList.add(ACTIVE_CLASS);
}

/**
 * Removes the 'active' tag from an autocomplete elmt
 * @param {Object} autocompleteListElmt
 * @return {void}
 */
function removeActiveTag(autocompleteListElmt) {
  for (elmt of autocompleteListElmt) {
    elmt.classList.remove(ACTIVE_CLASS);
  }
}

/**
 * Closes dropdown autocomplete list
 * @param {Object} elmnt
 * @return {void}
 */
function closeAllLists(elmnt) {
  const autoItems = document.getElementsByClassName(ITEM_CLASS);
  for (const item of autoItems) {
    if (item != elmnt && item != SEARCH_INPUT) {
      item.parentNode.removeChild(item);
    }
  }
}

/**
 * Current placeholder until servlet is created
 * @retur{Array<string>} arr
 */
function getListOfSuggestions() {
  const arr = ['Hello', 'Hi', 'Howdy'];
  return arr;
}

/**
 * Identifies and returns relevant suggestions in the arr
 * @param {Object} arr
 * @param {string} val
 * @return {Object} trimArr
 */
function getRelevantDataSuggestions(arr, val) {
  const trimArr = [];
  val = va.toUpperCase();
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
    //if (true) {
      const listElmt = document.createElement('DIV');
      listElmt.innerHTML = '<strong>' +
                           arrElt.substr(0, val.length) +
                           '</strong>';
      listElmt.innerHTML += arrElt.substr(val.length);
      listElmt.innerHTML += '<input type=\'hidden\' value=\'arrElt\'>';
      listElmt.addEventListener('click', function(e) {
        SEARCH_INPUT.value = SEARCH_INPUT.getElementsByTagName('input')[0].value;
        closeAllLists();
      });
      autocompleteList.appendChild(listElmt);
   // }
  }
}

/**
 * Event occurrance when input is provided to autocomplete field
 * @return {void}
 */
function giveSuggestions() {
  closeAllLists();
  const val = SEARCH_INPUT.value;
  if (!val) {
    return false;
  }
  const arr = getListOfSuggestions();
  SELECTED_SUGG_ELT = -1;
  const autocompleteList = document.createElement('div');
  autocompleteList.setAttribute('id', SEARCH_INPUT.id + LIST_ID);
  autocompleteList.setAttribute('class', ITEM_CLASS);
  SEARCH_INPUT.parentNode.appendChild(autocompleteList);
  const trimArr = getRelevantDataSuggestions(arr, val);
  displaySuggestions(trimArr, autocompleteList, val);
}

/**
 * Event occurance when arrow keys are pressed
 * @param {event} e
 * @return {void}
 */
function keyDown(e) {
  let listElmt = document.getElementById(SEARCH_INPUT.id + LIST_ID);
  if (listElmt) {
    listElmt = listElmt.getElementsByTagName('div');
  }
  if (e.keyCode == 40) {/* Up key */
    SELECTED_SUGG_ELT++;
    addActiveTag(listElmt);
  } else if (e.keyCode == 38) {/* Down key */
    SELECTED_SUGG_ELT--;
    addActiveTag(listElmt);
  } else if (e.keyCode == 13) {/* Enter key */
    e.preventDefault();
    if (SELECTED_SUGG_ELT > -1) {
      if (listElmt) {
        listElmt[SELECTED_SUGG_ELT].click();
      }
    }
  }
}

/* Event occurance when mouse is clicked */
document.addEventListener('click', (e) => {
  closeAllLists(e.target, SEARCH_INPUT);
});
