describe('DataTable Construction', () => {
  it('Will add correct columns/rows to DataTable from JSON', () => {
    const mockCalenderInfo = {};
    let expectedFreqData = {};
    let expectedTimelineData = {};
    let actualFreqData = {};
    let actualTimelineData = {};
    window.initCalendarCharts = () => {
      // TODO(ihsan314): adjust table and mock calendar info
      // when format of JSON is confirmed
      expectedFreqData = new google.visualization.DataTable();
      expectedTimelineData = new google.visualization.DataTable();
      actualFreqData = new google.visualization.DataTable();
      actualTimelineData = new google.visualization.DataTable();
      populateDataTables(actualFreqData, actualTimelineData, mockCalenderInfo);
    };
    expect(actualFreqData).toEqual(expectedFreqData);
    expect(actualTimelineData).toEqual(expectedTimelineData);
  });
});
