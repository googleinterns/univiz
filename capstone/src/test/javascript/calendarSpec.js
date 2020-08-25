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
   * @param {Array} values the array of pairs corresponding to a row.
   */
  addRow(values) {}

  /**
   * Dummy function for adding columns to mock DataTable class.
   *
   * @param {String} type the datatype of the column.
   * @param {String} name the name of the column.
   */
  addColumn(type, name) {}
};

describe('DataTable Construction', () => {
  it('Will add correct columns/rows to DataTable from JSON', () => {
    const mockDeadlineInfo = [
      {
        'openingDate': {'day': 1, 'month': 2, 'year': 3},
        'closingDate': {'day': 4, 'month': 5, 'year': 6},
      },
      {
        'openingDate': {'day': 7, 'month': 8, 'year': 9},
        'closingDate': {'day': 10, 'month': 11, 'year': 12},
      },
    ];
    spyOn(google.visualization.DataTable.prototype, 'addRow');
    spyOn(google.visualization.DataTable.prototype, 'addColumn');
    const actualFreqData = new google.visualization.DataTable();
    populateDataTables(actualFreqData, mockDeadlineInfo);
    expect(google.visualization.DataTable.prototype.addRow)
        .toHaveBeenCalledWith([
          new Date(
              mockDeadlineInfo[0]['openingDate']['year'],
              mockDeadlineInfo[0]['openingDate']['month'] - 1,
              mockDeadlineInfo[0]['openingDate']['day'],
          ),
          1,
        ]);
    expect(google.visualization.DataTable.prototype.addRow)
        .toHaveBeenCalledWith([
          new Date(
              mockDeadlineInfo[1]['closingDate']['year'],
              mockDeadlineInfo[1]['closingDate']['month'] - 1,
              mockDeadlineInfo[1]['closingDate']['day'],
          ),
          0,
        ]);
    expect(google.visualization.DataTable.prototype.addRow.calls.count())
        .toEqual(4);
    expect(google.visualization.DataTable.prototype.addColumn)
        .toHaveBeenCalledWith('date', 'Date');
    expect(google.visualization.DataTable.prototype.addColumn)
        .toHaveBeenCalledWith('number', 'Application Opens/Closes');
    expect(google.visualization.DataTable.prototype.addColumn.calls.count())
        .toEqual(2);
  });
});
