google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(() => {
  graphNetTuition();
  graphDemographics();
});
