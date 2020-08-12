describe('Autocomplete Suggests correct array', () => {
  it('Autocomplete selects correct items', () => {
    const fullData = ['Ahoy', 'Hello', 'Wattup'];
    const relevantData = ['Hello'];
    const retData = getRelevantDataSuggestions(fullData, 'H');
    expect(retData).toEqual(relevantData);
  });
});
