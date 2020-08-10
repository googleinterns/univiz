describe('Autocomplete Suggests correct array', () => {
  const data = ['Hello', 'Hi', 'Howdy', 'Yello'];
  document.getElementById('search').value = 'H';
  let autoItems = document.getElementsByClassName('autocomplete-items');
  for (let i = 0; i < autoItems.length; i++) {
    expect(autoItems[i].innerHTML).toBe(data[i]);
  }
});
