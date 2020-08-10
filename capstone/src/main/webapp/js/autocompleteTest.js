describe('Autocomplete Suggests correct array', function() {
  const data = ['Hello', 'Hi', 'Howdy', 'Yello'];
  document.getElementById('search').value = 'H';
  const autoItems = document.getElementsByClassName('autocomplete-items');
  let i = 0;
  for (item in autoItems) {
    expect(item.innerHTML).toBe(data[i]);
    i++;
  }
});
