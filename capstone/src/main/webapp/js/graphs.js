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

/**
 * Helper function to append elements in JSON
 * file to DataTable.
 *
 * @return {google.visualization.DataTable} data
 * the DataTable corresponding to the tuition data
 */
function deserializeTuitionData() {
  const data = new google.visualization.DataTable();
  fetch('/tuition-data').
      then((response) => response.json()).
      then((tuitionInfo) => {
        // iterate over tuitionInfo JSON
        // append to data
      });
  return data;
}
