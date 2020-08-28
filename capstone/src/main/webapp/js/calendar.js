/* eslint no-unused-vars: ["error", {"varsIgnorePattern": "drawCharts"}] */

/**
 * Callback function to generate charts.
 */
function drawCharts() {
  const frequencyData = new google.visualization.DataTable();
  const queryUrl = new URL(window.location.href);
  const ids = queryUrl.searchParams.get('id');
  fetch('/viz/deadlines?id='+ids).
      then((response) => response.json()).
      then((deadlineInfo) =>
        populateDataTables(frequencyData, deadlineInfo)).
      then(() => drawFrequencyChart(frequencyData));
}

/**
 * Generate frequency calendar chart of deadlines.
 * @param {google.visualization.DataTable} frequencyData the DataTable
 *     corresponding to the frequency of deadlines
 */
function drawFrequencyChart(frequencyData) {
  const options = {
    'title': 'Expected Application Heat Map',
  };
  const chart = new google.visualization.Calendar(
      document.getElementById('calendar-freq-map'));
  chart.draw(frequencyData, options);
}

/**
 * Helper function to transfer elements from JSON object with deadline
 * information to a DataTable.
 *
 * @param {google.visualization.DataTable} frequencyData the DataTable with the
 *     data the frequency chart will use. Will consist of sample deadlines for
 *     each of the user's selected colleges.
 *
 * @param {JSON} deadlineInfo the JSON object with the deadline information that
 *     will be added to the previously mentioned DataTables.
 */
function populateDataTables(frequencyData, deadlineInfo) {
  frequencyData.addColumn('date', 'Date');
  frequencyData.addColumn('number', 'Application Opens/Closes');
  deadlineInfo.forEach((college) => {
    frequencyData.addRow(
        [
          new Date(
              college['openingDate']['year'],
              // Javascript months start at 0, but Java months start at 1.
              // Hence the subtraction.
              college['openingDate']['month'] - 1,
              college['openingDate']['day'],
          ),
          1,
        ],
    );
    frequencyData.addRow(
        [
          new Date(
              college['closingDate']['year'],
              college['closingDate']['month'] - 1,
              college['closingDate']['day'],
          ),
          0,
        ],
    );
  });
}
