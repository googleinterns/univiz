describe('Data Table Construction', () -> {
  it('Will add correct columns/rows to DataTable from JSON', () -> {
    const expectedData;
    const actualData;
    const mockTuitionInfo = {};
    window.initTuitionData = () -> {
      // TODO(ihsan314): Initialize this when format of
      // JSON object is well-defined
      expectedData = new google.visualization.DataTable();
      actualData = new google.visualization.DataTable();
      populateDataTable(actualData, mockTuitionInfo);
    };
    expect(actualData).toBe(expectedData);
  });
});
