/**
 * The callback function that will generate the double bar graph. Uses
 * Google Charts API and JSON object consisting of college tuition data.
 */
function graphNetTuition() {
  const options = {
    title: 'Net Price of Colleges',
  };
  const chart =
    new google.visualization.ColumnChart(document.getElementById('data'));
  deserializeTuitionData().then((data) => chart.draw(data, options));
}

/**
 * Helper function to fetch elements from JSON servlet.
 *
 * @return {google.visualization.DataTable} data the DataTable corresponding to
 *     the tuition data
 */
async function deserializeTuitionData() {
  const data = new google.visualization.DataTable();
  await fetch('/tuition-data')
      .then((response) => response.json())
      .then((tuitionInfo) => populateDataTable(data, tuitionInfo));
  return data;
}

/**
 * Helper function to transfer elements from JSON object to a DataTable.
 *
 * @param {google.visualization.DataTable} data the DataTable corresponding to
 *     the tuition data
 *
 * @param {JSON} tuitionInfo the JSON object corresponding to the tuition data
 *     that will be graphed.
 */
function populateDataTable(data, tuitionInfo) {
  data.addColumn('string', 'Your Colleges');
  data.addColumn('number', 'Tuition Cost (USD)');
  tuitionInfo.forEach((college) => {
    data.addRow([college['name'], college['avgCost']]);
  });
}
