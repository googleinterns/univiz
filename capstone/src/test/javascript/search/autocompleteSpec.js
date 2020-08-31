describe('Autocomplete Suggests correct array', () => {
  it('Tests Proper List Element Creation Simple', () => {
    const arrElt = 'Hello';
    const val = 'He';
    const listElmtActual = createListElmt(arrElt, val).toString();
    const listElmtExpected = document.createElement('div');
    listElmtExpected.innerHTML = '<strong>He</strong>llo';
    expect(listElmtExpected).toEqual(listElmtActual);
  });
  
  it('Tests Proper List Element Creation Complex', () => {
    const arrElt = 'Hallo Hello';
    const val = 'He';
    const listElmtActual = createListElmt(arrElt, val).toString();
    const listElmtExpected = document.createElement('div');
    listElmtExpected.innerHTML = 'Hallo <strong>He</strong>llo';
    expect(listElmtExpected).toEqual(listElmtActual);
  });
});
