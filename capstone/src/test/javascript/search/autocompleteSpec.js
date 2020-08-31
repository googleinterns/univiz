describe('Autocomplete Suggests correct array', () => {
  it('Tests Proper List Element Creation Simple', () => {
    const arrElt = {'school.name': 'Hello', 'collegeId': {'id': 0}};
    const val = 'He';
    const listElmtActual = createListElmt(arrElt, val);
    const listElmtExpected = '<div><strong>He</strong>llo</div>';
    expect(listElmtExpected).toEqual(listElmtActual);
  });
  
  it('Tests Proper List Element Creation Complex', () => {
    const arrElt = {school.name: 'Hallo Hello', collegeId: {id: 0}};
    const val = 'He';
    const listElmtActual = createListElmt(arrElt, val);
    const listElmtExpected = '<div>Hallo <strong>He</strong>llo</div>';
    expect(listElmtExpected).toEqual(listElmtActual);
  });
});
