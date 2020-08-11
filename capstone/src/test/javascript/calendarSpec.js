const google = {};
google.visualization = {};
google.visualization.DataTable = class {
  /**
   * Empty constructor for mock DataTable.
   */
  constructor() {}
}

describe('DataTable Construction', () => {
  it('Will add correct columns/rows to DataTable from JSON', () => {
    // TODO(ihsan314): adjust table and mock calendar info
    // when format of JSON is confirmed
    const mockCalenderInfo = {};
    const expectedFreqData = new google.visualization.DataTable();
    const expectedTimelineData = new google.visualization.DataTable();
    const actualFreqData = new google.visualization.DataTable();
    const actualTimelineData = new google.visualization.DataTable();
    const populateDataTables(actualFreqData, actualTimelineData, mockCalenderInfo);
    expect(actualFreqData).toEqual(expectedFreqData);
    expect(actualTimelineData).toEqual(expectedTimelineData);
  });
});
