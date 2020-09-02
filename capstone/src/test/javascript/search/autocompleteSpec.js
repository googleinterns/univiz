describe('Different autocomplete functions work as expected', () => {
  it('Tests List Element Creation with Substring at the beginning', () => {
    const sampleSuggestion = 'Hello';
    const sampleUserProvidedSubstring = 'He';
    const listElementActual =
      createAutocompleteListElement(
          sampleSuggestion, sampleUserProvidedSubstring);
    const listElementExpected = '<strong>He</strong>llo';
    expect(listElementExpected).toEqual(listElementActual.innerHTML);
  });
  it('Tests List Element Creation with Substring in the middle', () => {
    const sampleSuggestion = 'Hallo Hello';
    const sampleUserProvidedSubstring = 'He';
    const listElementActual =
      createAutocompleteListElement(
          sampleSuggestion, sampleUserProvidedSubstring);
    const listElementExpected = 'Hallo <strong>He</strong>llo';
    expect(listElementExpected).toEqual(listElementActual.innerHTML);
  });
  it('Tests List Element Creation with Substring at the end', () => {
    const sampleSuggestion = 'Hallo Hello';
    const sampleUserProvidedSubstring = 'ello';
    const listElementActual =
      createAutocompleteListElement(
          sampleSuggestion, sampleUserProvidedSubstring);
    const listElementExpected = 'Hallo H<strong>ello</strong>';
    expect(listElementExpected).toEqual(listElementActual.innerHTML);
  });
  it('Tests Autocomplete can actually keep track of colleges', () => {
    const fakeValidSuggestions = {'collegeId': {'id': 0},
      'collegeName': 'Stanford University',
    };
    const expectedInnerHtml = 'Stanford University<br><button>Remove</button>';
    const actualListElement = keepTrackOfChosenColleges(fakeValidSuggestions);
    expect(actualListElement.innerHTML).toEqual(expectedInnerHtml);
  });
});
