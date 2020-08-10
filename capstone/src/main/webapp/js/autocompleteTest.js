describe('Autocomplete Suggests correct array', function() {
  const data = ['Hello', 'Hi', 'Howdy', 'Yello'];
  document.getElementById('search').value = 'H';
  const autoItems = document.getElementsByClassName('autocomplete-items');
  for (let i = 0; i < autoItems.length; i++) {
    expect(autoItems[i].innerHTML).toBe(data[i]);
  }
});
