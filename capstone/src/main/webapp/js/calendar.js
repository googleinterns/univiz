window.initCalendarCharts = () => {
  google.charts.load('current', {'packages': ['calendar', 'timeline']});
  google.charts.setOnLoadCallback(drawCharts);

  /**
   * Callback function to generate charts.
   */
  function drawCharts() {
    const freqData = new google.visualization.DataTable();
    const timelineData = new google.visualization.DataTable();
    fetch('/suggested-cal').
        then((response) => response.json()).
        then((deadlineInfo) =>
          populateDataTables(freqData, timelineData, deadlineInfo));
    drawFrequencyChart(freqData);
    drawTimelineChart(timelineData);
  }
};

/**
 * Generate frequency calendar chart of deadlines.
 * @param {google.visualization.DataTable} freqData the DataTable corresponding
 *     to the frequency of deadlines
 */
function drawFrequencyChart(freqData) {
  const options = {
    'title': 'Expected Application Heat Map',
  };
  const chart = new google.visualization.Calendar(
      document.getElementById('calendar-freq-map'));
  chart.draw(freqData, options);
}

/**
 * Generate suggested timeline of actions related to deadlines.
 * @param {google.visualization.DataTable} timelineData the DataTable corresponding
 *     to the suggested actions to take before the deadlines
 */
function drawTimelineChart(timelineData) {
  const options = {
    'title': 'Suggested Application Timeline',
  };
  const chart = new google.visualization.Timeline(
      document.getElementById('timeline-chart'));
  chart.draw(timelineData, options);
}

/**
 * Helper function to transfer elements from JSON object with deadline information
 * to two DataTables.
 *
 * @param {google.visualization.DataTable} freqData the DataTable with the data the
 *     frequency chart will use. Will consist of sample deadlines for each of the user's
 *     selected colleges.
 *
 * @param {google.visualization.DataTable} timelineData * the DataTable with the
 *     data for the timeline chart. Will consist of suggested courses of action for
 *     the user based on sample deadlines.
 *
 * @param {JSON} deadlineInfo the JSON object with the deadline and timeline
 *     information that will be added to the previously mentioned DataTables.
 *
 */
function populateDataTables(freqData, timelineData, deadlineInfo) {
  // TODO(ihsan314): update freqData datatable
  // and update timelineData datatable
}
