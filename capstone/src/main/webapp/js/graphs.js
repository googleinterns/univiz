window.initTuitionData = () => {
  google.charts.load('current', {packages: ['corechart', 'bar']});
  google.charts.setOnLoadCallback(graphGrossNetTuition);

  /**
   * The callback function that will generate the double
   * bar graph. Uses Google Charts API and JSON object
   * consisting of college tuition data.
   */
  function graphGrossNetTuition() {
    const data = deserializeTuitionData();
    const options = {
      title: 'Gross and Net Price of Colleges',
      hAxis: {
        title: 'Your Colleges',
      },
      vAxis: {
        title: 'Tuition Price (USD)',
      },
    };
    const chart =
      new google.visualization.ColumnChart(document.getElementById('data'));
    chart.draw(data, options);
  }
};

/**
 * Helper function to fetch elements from JSON servlet.
 *
 * @return {google.visualization.DataTable} data
 * the DataTable corresponding to the tuition data
 */
function deserializeTuitionData() {
  const data = new google.visualization.DataTable();
  fetch('/tuition-data')
    .then((response) => response.json())
    .then((tuitionInfo) => populateDataTable(data, tuitionInfo));
  return data;
}

/**
 * Helper function to transfer elements from
 * JSON object to a DataTable.
 *
 * @param {google.visualization.DataTable} data
 * the DataTable corresponding to the tuition data
 *
 * @param {JSON} tuitionInfo the JSON object
 * corresponding to the tuition data that
 * will be graphed.
 */
function populateDataTable(data, tuitionInfo) {
  // TODO(ihsan314): iterate over tuitionInfo and append to data
  // when format of JSON is confirmed
}
