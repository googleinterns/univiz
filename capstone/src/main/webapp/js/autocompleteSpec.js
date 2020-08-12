describe('Autocomplete Suggests correct array', function() {
  let search;

  beforeEach(function() {
   let id = 'search';
   search = $('<input id='+id+'/>');
   $(document.body).append(search);
  });

  it('Autocomplete selects correct items', () => {
   const fullData = ['Ahoy', 'Hello', 'Wattup'];
   const relevantData = ['Hello'];
   autocomplete(fullData);
  })

  afterEach(function(){
    search.remove();
    search = null;
  });
});
