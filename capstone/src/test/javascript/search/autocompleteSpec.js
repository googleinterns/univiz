describe('Autocomplete provides correct list element', () => {
  it('Tests Proper List Element Creation Simple', () => {
    const arrElt = 'Hello';
    const val = 'He';
    const listElmtActual = createListElmt(arrElt, val).toString();
    const listElmtExpected = '<strong>He</strong>llo';
    expect(listElmtExpected).toEqual(listElmtActual);
  });
  it('Tests Proper List Element Creation Complex', () => {
    const arrElt = 'Hallo Hello';
    const val = 'He';
    const listElmtActual = createListElmt(arrElt, val).toString();
    const listElmtExpected = 'Hallo <strong>He</strong>llo';
    expect(listElmtExpected).toEqual(listElmtActual.innerHTML);
  });
});
