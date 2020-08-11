describe('Autocomplete Suggests correct array', function() {
  it('Autocomplete selects the right suggestion items', () => {
   const fullData = ['Ahoy', 'Hello', 'Wattup'];
   const relevantData = ['Hello'];
   autocomplete(fullData);
  })
});
