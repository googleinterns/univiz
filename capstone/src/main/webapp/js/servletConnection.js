/**
 *
 */
function getSuggestions() {
  fetch('/search').then((response) => response.json()).then((suggestions) => {
    console.log(suggestions);
    getSuggestions(suggestions);
    /*TODO: AUTOCOMPLETE FUNCTION HERE*/
  });
}

/**
 *
 */
function postPartialCollegeName() {
  /*FUNCTION MEAT*/
}

/**
 *
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
