describe('Autocomplete provides correct list element', () => {
  it('Tests List Element Creation with Substring at the beginning', () => {
    const sampleSuggestion = 'Hello';
    const sampleUserProvidedSubstring = 'He';
    const listElementActual = createAutocompleteListElement(sampleSuggestion, sampleUserProvidedSubstring);
    const listElementExpected = '<strong>He</strong>llo';
    expect(listElementExpected).toEqual(listElementActual.innerHTML);
  });
  it('Tests List Element Creation with Substring in the middle', () => {
    const sampleSuggestion = 'Hallo Hello';
    const sampleUserProvidedSubstring = 'He';
    const listElementActual = createAutocompleteListElement(sampleSuggestion, sampleUserProvidedSubstring);
    const listElementExpected = 'Hallo <strong>He</strong>llo';
    expect(listElementExpected).toEqual(listElementActual.innerHTML);
  });
  it('Tests List Element Creation with Substring at the end', () => {
    const sampleSuggestion = 'Hallo Hello';
    const sampleUserProvidedSubstring = 'ello';
    const listElementActual = createAutocompleteListElement(sampleSuggestion, sampleUserProvidedSubstring);
    const listElementExpected = 'Hallo H<strong>ello</strong>';
    expect(listElementExpected).toEqual(listElementActual.innerHTML);
  });
});
