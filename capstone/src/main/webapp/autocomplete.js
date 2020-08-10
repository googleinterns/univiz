/**
 * Autocomplete
 * @param {inp}
 * @arg {arr}
 * @return {void}
 * Attributing this code to: w3 schools
 * Link: https://www.w3schools.com/howto/howto_js_autocomplete.asp
*/
function autocomplete(inp, arr) {
  let currentFocus;
  /*Event occurrance when input is provided to autocomplete field*/
  inp.addEventListener('input', function(e) {
    closeAllLists();
    const val = this.value;
    if (!val) { 
      return false;
    }
    currentFocus = -1;

    const autocompleteList = document.createElement('DIV');
    autocompleteList.setAttribute('id', this.id + 'autocomplete-list');
    autcompleteList.setAttribute('class', 'autocomplete-items');
    this.parentNode.appendChild(autocompleteList);
    

    for (let i = 0; i < arr.length; i++) {
      if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
        
	const listElement = document.createElement('DIV');
        listElement.innerHTML = '<strong>' + arr[i].substr(0, val.length) + '</strong>';
        listElement.innerHTML += arr[i].substr(val.length);
        listElement.innerHTML += '<input type='+'hidden'+'value='+arr[i]+'>';
        listElement.addEventListener('click', function(e) {
          inp.value = this.getElementsByTagName('input')[0].value;
          closeAllLists();
        });
        
	autocompleteList.appendChild(listElement);
      }
    }
  });

  /*Event occurance when arrow keys are pressed*/
  inp.addEventListener('keydown', function(e) {
    let autocompleteListElmt = document.getElementById(this.id + 'autocomplete-list');
    if (autocompleteListElmt) {
      autocompleteListElmt = autocompleteListElmt.getElementsByTagName('div');
    }
    if (e.keyCode == 40) { /*Up key*/
      currentFocus++;
      addActiveTag(autocompleteListElmt);
    } else if (e.keyCode == 38) { /*Down key*/
      currentFocus--;
      addActiveTag(autocompleteListElmt);
    } else if (e.keyCode == 13) { /*Enter key*/
      e.preventDefault();
      if (currentFocus > -1) {
        if (autocompleteListElmt) {
          autocompleteListElmt[currentFocus].click();
        }
      }
    }
  });

  /*Adds the 'active' tag to an autocomplete elmt*/
  function addActiveTag(autocompleteListElmt) {
    if (!autocompleteListElmt) {
      return false;
    }
    removeActiveTag(autocompleteListElmt);
    if (currentFocus >= autocompleteListElmt.length) {
      currentFocus = 0;
    } else if (currentFocus < 0) {
      currentFocus = (autocompleteListElmt.length - 1);
    }
    autocompleteListElmt[currentFocus].classList.add('autocomplete-active');
  }

  /*Removes the 'active' tag from an autocomplete elmt*/
  function removeActiveTag(autocompleteListElmt) {
    for (let i = 0; i < autocompleteListElmt.length; i++) {
      autocompleteListElmt[i].classList.remove('autocomplete-active');
    }
  }

  /*Closes dropdown autocomplete list(s)*/
  function closeAllLists(elmnt) {
    const autocompleteItems = document.getElementsByClassName('autocomplete-items');
    for (let i = 0; i < autocompleteItems.length; i++) {
      if (elmnt != autocompleteItems[i] && elmnt != inp) {
        autocompleteItems[i].parentNode.removeChild(autocompleteItems[i]);
      }
    }
  }

  /*Event occurance when mouse is clicked*/
  document.addEventListener('click', function(e){
    closeAllLists(e.target);
  });
}
