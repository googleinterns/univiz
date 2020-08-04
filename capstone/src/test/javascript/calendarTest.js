describe('', function() {
  it('', function() {
    const script = document.createElement('script');
    script.src = 'https://www.gstatic.com/charts/loader.js';
    script.defer = true;

    window.initMap = function() {
      // TODO(ihsan314): adjust table and mock calendar info
      // when format of JSON is confirmed
      const mockCalenderInfo = {};
      const expectedFreqData = new google.visualization.DataTable();
      const expectedTimelineData = new google.visualization.DataTable();
      const actualFreqData = new google.visualization.DataTable();
      const actualTimelineData = new google.visualization.DataTable();
      populateDataTables(actualFreqData, actualTimelineData, mockCalenderInfo);
      expect(actualFreqData).toBe(expectedFreqData);
      expect(actualTimelineData).toBe(expectedTimelineData);
    };
    document.head.appendChild(script);
  });
});
