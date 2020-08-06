var google = {};
google.visualization = {};

google.visualization.DataTable = class {
  constructor() {}
};

describe('Data Table Construction', function() {
  it('Will add correct columns/rows to DataTable from JSON', function() {
    const expectedData = new google.visualization.DataTable();
    const actualData = new google.visualization.DataTable();
    const mockTuitionInfo = {};
    spyOn(window, 'fetch').and.returnValue(Promise.resolve({json: () => {},}));
    
    // TODO(ihsan314): Initialize this when format of
    // JSON object is well-defined
    const actual = deserializeTuitionData();
    
    expect(actualData).toEqual(expectedData);
  });
});
