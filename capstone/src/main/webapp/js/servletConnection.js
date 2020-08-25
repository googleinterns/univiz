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

function postPartialCollegeName() {

}
