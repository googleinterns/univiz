/**
 * Sends final college list to servlet
 * @return{void}
 */
function sendCollegeInformation() {
  const params = new URLSearchParams();
  const listItems = document.querySelector('ul').children;
  const listArray = Array.from(listItems);
  const index = 0;
  for (elt in listArray) {
    const paramId = 'college' + index;
    params.append(paramId, elt.innerHTML);
    index++;
  }
  params.append('collegeNumber', index);
  fetch('/search', {method: 'POST', body: params});
}
