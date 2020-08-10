describe('DataTable Construction 2', function() {
  it('Will add correct columns/rows to DataTable from JSON', function() {
    const mockCalenderInfo = {};
    let expectedFreqData;
    let expectedTimelineData;
    let actualFreqData;
    let actualTimelineData;
    window.initMap = function() {
      // TODO(ihsan314): adjust table and mock calendar info
      // when format of JSON is confirmed
      dum
      expectedFreqData = new google.visualization.DataTable();
      expectedTimelineData = new google.visualization.DataTable();
      actualFreqData = new google.visualization.DataTable();
      actualTimelineData = new google.visualization.DataTable();
      populateDataTables(actualFreqData, actualTimelineData, mockCalenderInfo);
    };
    expect(actualFreqData).toBe(expectedFreqData);
    expect(actualTimelineData).toBe(expectedTimelineData);
  });
});
