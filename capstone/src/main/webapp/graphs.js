google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(graphGrossNetTuition);

/**
 * The callback function that will generate the double
 * bar graph. Uses Google Charts API and JSON object
 * consisting of college tuition data.
 */
function graphGrossNetTuition() {
  const data = new google.visualization.DataTable();
  fetch('/financial-data').
      then((response) => response.json()).
      then((tuitionInfo) => {
        // iterate over tuitionInfo JSON
        // append to data
      });

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
