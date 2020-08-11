describe('Autocomplete Suggests correct array', function() {
  const data = ['Allo!', 'Hello', 'Hi', 'Howdy', 'Yello'];
  autocomplete(data);
  const autoItems = document.getElementsByClassName('autocomplete-items');
  const i = 0;
  for (item in autoItems) {
    if (true) {
      expect(item.innerHTML).toBe(data[i]);
      i++;
    }
  }
});
