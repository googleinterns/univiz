describe('Data Table Construction', function() {
  it('Will add correct columns/rows to DataTable from JSON', function() {
    // TODO(ihsan314): Change this when format of JSON object is well-defined

    window.initMap = function() {
      const mockTuitionInfo = {};
      const expectedData = new google.visualization.DataTable();
      const actualData = new google.visualization.DataTable();
      populateDataTable(actualData, mockTuitionInfo);
      expect(actualData).toBe(expectedData);
    };
  });
});
