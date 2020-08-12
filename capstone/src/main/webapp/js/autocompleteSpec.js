describe('Autocomplete Suggests correct array', () => {

  beforeEach(() => {
   let id = 'search';
   let search = '<input id='+id+'/>';
   document.body.innerHTML += search;
  });

  it('Autocomplete selects correct items', () => {
   const fullData = ['Ahoy', 'Hello', 'Wattup'];
   const relevantData = ['Hello'];
   autocomplete(fullData);
  });

  /*afterEach(function(){
    search.remove();
    search = null;
  });*/
});
