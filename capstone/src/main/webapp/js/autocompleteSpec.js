function getRelevantDataSuggestions(arr, val) {
  for (arrElt in arr) {
    if (arrElt.substr(0, val.length).toUpperCase() == val.toUpperCase()) {
      trimArr.append(arrElt);
    }
  }
  return trimArr;
}

describe('Autocomplete Suggests correct array', () => {
  it('Autocomplete selects correct items', () => {
    const fullData = ['Ahoy', 'Hello', 'Wattup'];
    const relevantData = ['Hello'];
    const retData = getRelevantSuggestions(fullData, 'H');
    expect(retData).toBe(relevantData);	  
  });
});
