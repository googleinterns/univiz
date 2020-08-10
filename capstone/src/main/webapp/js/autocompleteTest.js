describe('Autocomplete Suggests correct array', function() {
  const data = ['Hello', 'Hi', 'Howdy', 'Yello'];
  document.getElementById('search').value = 'H';
  const autoItems = document.getElementsByClassName('autocomplete-items');
  const i = 0;
  for (item in autoItems) {
    if (true) { 
      expect(item.innerHTML).toBe(data[i]);
      i++;
    }
  }
});
