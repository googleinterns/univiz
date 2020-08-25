/**
 * Gets suggestions to display to the users
 * @return{void}
 */
function getSuggestions() {
  fetch('/search').then((response) => response.json()).then((suggestions) => {
    getSuggestions(suggestions);
    /*TODO: AUTOCOMPLETE FUNCTION HERE*/
  });
}

/**
 * Sends partial college name to the servlet
 * @return{void}
 */
function postPartialCollegeName() {
  /*FUNCTION MEAT*/
}

/**
 * Sends final college list to servlet
 * @return{void}
 */
function sendCollegeInformation() {
  const params = new URLSearchParams();
  const listItems = document.querySelector('ul').children;
  const listArray = Array.from(listItems);
  for (elt in listArray) {
    params.append('college', elt.innerHTML);
  }
  fetch('/search', {method: 'POST', body: params});
}
