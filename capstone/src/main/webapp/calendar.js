google.charts.load('current', {'packages':['calendar', 'timeline']});
google.charts.setOnLoadCallback(drawCharts);

function drawCharts() {
  drawFrequencyChart();
  drawTimelineChart();
}

function drawFrequencyChart() {
  const data = new google.visualization.DataTable();
  const options = {
    'title': 'Expected Application Heat Map'
  }
  const chart = new google.visualization.Calendar(document.getElementById('calendar-freq-map'));
  chart.draw(data, options);
}

function drawTimelineChart() {
  const data = new google.visualization.DataTable();
  const options = {
    'title': 'Suggested Application Timeline'
  }
  const chart = new google.visualization.Timeline(document.getElementById('timeline-chart'));
  chart.draw(data, options);
}
