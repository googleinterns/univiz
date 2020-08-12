describe('Autocomplete Suggests correct array', () => {
  it('Autocomplete selects correct items', () => {
    const fullData = ['Ahoy', 'Hello', 'Wattup'];
    const relevantData = ['Hello'];
    const retData = getRelevantSuggestions(fullData, 'H');
    expect(retData).toBe(relevantData);	  
  });
});
