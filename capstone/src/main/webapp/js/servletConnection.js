/**
 * Sends final college list to servlet
 * @return{void}
 */
function sendCollegeInformation() {
  const listItems = document.querySelector('ul').children;
  const listArray = Array.from(listItems);
  let urlHtml = 'dashboard.html?id=';
  for (const elt of listArray) {
    urlHtml += elt.id;
  }
  window.location.href = urlHtml;
}
