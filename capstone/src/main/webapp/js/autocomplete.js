const searchInput = document.getElementById('search');
let selectedSuggElt = -1;
const itemClass = 'autocomplete-items';
const activeClass = 'autocomplete-active';
const listId = 'autocomplete-list';
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
  if (selectedSuggElt >= autocompleteListElmt.length) {
    selectedSuggElt = 0;
  } else if (selectedSuggElt < 0) {
    selectedSuggElt = (autocompleteListElmt.length - 1);
  }
  autocompleteListElmt[selectedSuggElt].classList.add(activeClass);
}

/**
 * Removes the 'active' tag from an autocomplete elmt
 * @param {Object} autocompleteListElmt
 * @return {void}
 */
function removeActiveTag(autocompleteListElmt) {
  for (elmt of autocompleteListElmt) {
    elmt.classList.remove(activeClass);
  }
}

/**
 * Closes dropdown autocomplete list
 * @param {Object} elmnt
 * @param {Object} inp
 * @return {void}
 */
function closeAllLists(elmnt) {
  const autoItems = document.getElementsByClassName(itemClass);
  for (const item of autoItems) {
    if (item != elmnt && item != searchInput) {
      item.parentNode.removeChild(item);
    }
  }
}

/**
 * Current placeholder until servlet is created
 * @return{Object} arr
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

/**
 * Event occurrance when input is provided to autocomplete field
 * @return {void}
 */
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

/**
 * Event occurance when arrow keys are pressed
 * @param {event} e
 * @return {void}
 */
function keyDown(e) {
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
