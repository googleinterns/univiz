const google = {};
google.visualization = {};
google.visualization.DataTable = class {
  /**
   * Dummy constructor for mock DataTable class.
   */
  constructor() {}

  /**
   * Dummy function for adding rows to mock DataTable class.
   *
   * @param {Object} value1 the value for the first column.
   * @param {Object} value2 the value for the second column.
   */
  addRow(value1, value2) {}

  /**
   * Dummy function for adding columns to mock DataTable class.
   *
   * @param {String} type the datatype of the column.
   * @param {String} name the name of the column.
   */
  addColumn(type, name) {}
};


describe('Data Table Construction', async () => {
  it('Will add correct columns/rows to DataTable from JSON', () => {
    const mockTuitionInfo = [
      {'name': 'University of British Columbia', 'avgCost': 5000},
      {'name': 'Simon Fraser University', 'avgCost': 4000},
    ];
    spyOn(window, 'fetch')
        .and.returnValue(Promise.resolve({json: () => mockTuitionInfo}));
    spyOn(google.visualization.DataTable.prototype, 'addRow');
    spyOn(google.visualization.DataTable.prototype, 'addColumn');
    await deserializeTuitionData();
    expect(google.visualization.DataTable.prototype.addRow)
        .toHaveBeenCalledWith(
            mockTuitionInfo[0]['name'],
            mockTuitionInfo[0]['avgCost'],
        );
    expect(google.visualization.DataTable.prototype.addRow)
        .toHaveBeenCalledWith(
            mockTuitionInfo[1]['name'],
            mockTuitionInfo[1]['avgCost'],
        );
    expect(google.visualization.DataTable.prototype.addRow.calls.count())
        .toEqual(2);
    expect(google.visualization.DataTable.prototype.addColumn)
        .toHaveBeenCalledWith('string', 'Your Colleges');
    expect(google.visualization.DataTable.prototype.addColumn)
        .toHaveBeenCalledWith('number', 'Tuition Cost (USD)');
    expect(google.visualization.DataTable.prototype.addColumn.calls.count())
        .toEqual(2);
  });
});
