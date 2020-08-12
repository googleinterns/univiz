describe('Autocomplete Suggests correct array', () => {
  let search; 

  beforeEach(() => {
   let id = 'search';
   search = '<input id='+id+'/>';
   document.body.innerHTML += search;
  });

  it('Autocomplete selects correct items', () => {
   const fullData = ['Ahoy', 'Hello', 'Wattup'];
   const relevantData = ['Hello'];
   autocomplete(search, fullData);
  });

  /*afterEach(function(){
    search.remove();
    search = null;
  });*/
});
