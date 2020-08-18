describe('Autocomplete Suggests correct array', () => {
  it('Autocomplete selects correct item', () => {
    const fullData = ['Ahoy', 'Hello', 'Wattup'];
    const relevantData = ['Hello'];
    const retData = getRelevantDataSuggestions(fullData, 'H');
    expect(retData).toEqual(relevantData);
  });
  it('Autocomplete selects multiple correct items', () => {
    const fullData = ['Ahoy', 'Hello', 'Hi', 'Wattup'];
    const relevantData = ['Hello', 'Hi'];
    const retData = getRelevantDataSuggestions(fullData, 'H');
    expect(retData).toEqual(relevantData);
  });
});
