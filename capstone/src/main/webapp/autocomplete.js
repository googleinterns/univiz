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

  inp.addEventListener('keydown', function(e) {
    let autocompleteListElmt = document.getElementById(this.id + 'autocomplete-list');
    if (autocompleteListElmt) {
      autocompleteListElmt = autocompleteListElmt.getElementsByTagName('div');
    }
    if (e.keyCode == 40) {
      currentFocus++;
      addActive(autocompleteListElmt);
    } else if (e.keyCode == 38) { 
      currentFocus--;
      addActive(autocompleteListElmt);
    } else if (e.keyCode == 13) {
      e.preventDefault();
      if (currentFocus > -1) {
        if (autocompleteListElmt) {
          autocompleteListElmt[currentFocus].click();
        }
      }
    }
  });

  function addActive(x) {
    if (!x) {
      return false;
    }
    removeActive(x);
    if (currentFocus >= x.length) {
      currentFocus = 0;
    }
    if (currentFocus < 0) {
      currentFocus = (x.length - 1);
    }
    x[currentFocus].classList.add('autocomplete-active');
  }

  function removeActive(x) {
    for (let i = 0; i < x.length; i++) {
      x[i].classList.remove('autocomplete-active');
    }
  }

  function closeAllLists(elmnt) {
    const autocomplete_items = document.getElementsByClassName('autocomplete-items');
    for (let i = 0; i < autocomplete_items.length; i++) {
      if (elmnt != autocomplete_items[i] && elmnt != inp) {
        autocomplete_items[i].parentNode.removeChild(autocomplete_items[i]);
      }
    }
  }

  document.addEventListener('click', function(e){
    closeAllLists(e.target);
  });
}
