const google = {};
google.visualization = {};
google.visualization.DataTable = class {
  /**
   * Dummy constructor for mock DataTable class.
   */
  constructor() {}
};

describe('Data Table Construction', () => {
  it('Will add correct columns/rows to DataTable from JSON', () => {
    // TODO(ihsan314): Initialize this when format of
    // JSON object is well-defined
    const mockTuitionInfo = {};
    const expectedData = new google.visualization.DataTable();
    const actualData = new google.visualization.DataTable();
    console.log(expectedData);
    console.log(actualData);
    populateDataTable(actualData, mockTuitionInfo);
    expect(actualData).toEqual(expectedData);
  });
});
