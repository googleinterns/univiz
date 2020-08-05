describe('', function() {
  it('', function() {
    const mockCalenderInfo = {};
    const expectedFreqData;
    const expectedTimelineData;
    const actualFreqData;
    const actualTimelineData;
    window.initMap = function() {
      // TODO(ihsan314): adjust table and mock calendar info
      // when format of JSON is confirmed
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
