/* eslint no-unused-vars: ["error",{"varsIgnorePattern":"graphDemographics"}] */

/**
 * The callback function that will generate graphs of college statistics. Uses
 * Google Charts API and JSON object consisting of college demographical data.
 */
function graphDemographics() {
  const admissionRateOptions = {
    title: 'Admission Rate of Colleges',
  };
  const avgSatOptions = {
    title: 'Average SAT Score of Colleges',
  };
  const numUndergradsOptions = {
    title: 'Number of Undergraduate Students in Colleges',
  };
  const genderRatioOptions = {
    title: 'Female/Male Ratio in Colleges',
  };
  const admissionRateChart =
    new google.visualization.ColumnChart(
        document.getElementById('admission-rate-data'),
    );
  const avgSatChart =
    new google.visualization.ColumnChart(
        document.getElementById('avg-sat-data'),
    );
  const numUndergradsChart =
    new google.visualization.ColumnChart(
        document.getElementById('num-undergrads-data'),
    );
  const genderRatioChart =
    new google.visualization.ColumnChart(
        document.getElementById('gender-ratio-data'),
    );
  const admissionRateDataTable = new google.visualization.DataTable();
  const numUndergradsDataTable = new google.visualization.DataTable();
  const avgSatDataTable = new google.visualization.DataTable();
  const genderRatioDataTable = new google.visualization.DataTable();
  const queryUrl = new URL(window.location.href);
  const ids = queryUrl.searchParams.get('id');
  fetch('/viz/stats?id='+ids)
      .then((response) => response.json())
      .then((data) => {
        populateAdmissionRateDataTable(admissionRateDataTable, data);
        populateNumUndergradsDataTable(numUndergradsDataTable, data);
        populateAvgSatDataTable(avgSatDataTable, data);
        populateGenderRatioDataTable(genderRatioDataTable, data);
      }).then(() => {
        admissionRateChart.draw(admissionRateDataTable, admissionRateOptions);
        numUndergradsChart.draw(numUndergradsDataTable, numUndergradsOptions);
        avgSatChart.draw(avgSatDataTable, avgSatOptions);
        genderRatioChart.draw(genderRatioDataTable, genderRatioOptions);
      });
}

/**
 * Helper function to transfer elements from JSON object to a DataTable.
 *
 * @param {google.visualization.DataTable} data the DataTable corresponding to
 *     the admission data
 *
 * @param {JSON} admissionInfo the JSON object corresponding to the admission
 *     rate data that will be graphed.
 */
function populateAdmissionRateDataTable(data, admissionInfo) {
  data.addColumn('string', 'Your Colleges');
  data.addColumn('number', 'Admission Rate (%)');
  admissionInfo.forEach((college) => {
    data.addRow([college['name'], college['admissionRate']]);
  });
}

/**
 * Helper function to transfer elements from JSON object to a DataTable.
 *
 * @param {google.visualization.DataTable} data the DataTable corresponding to
 *     the SAT data
 *
 * @param {JSON} avgSatInfo the JSON object corresponding to the average SAT
 *     score data that will be graphed.
 */
function populateAvgSatDataTable(data, avgSatInfo) {
  data.addColumn('string', 'Your Colleges');
  data.addColumn('number', 'Average SAT score');
  avgSatInfo.forEach((college) => {
    data.addRow([college['name'], college['avgSat']]);
  });
}

/**
 * Helper function to transfer elements from JSON object to a DataTable.
 *
 * @param {google.visualization.DataTable} data the DataTable corresponding to
 *     the data on the number of undergraduate students per college
 *
 * @param {JSON} numUndergradsInfo the JSON object corresponding to the data on
 *     the number of undergrads per college, which will be graphed.
 */
function populateNumUndergradsDataTable(data, numUndergradsInfo) {
  data.addColumn('string', 'Your Colleges');
  data.addColumn('number', 'Number of Undergraduate Students');
  numUndergradsInfo.forEach((college) => {
    data.addRow([college['name'], college['numOfUndergrads']]);
  });
}

/**
 * Helper function to transfer elements from JSON object to a DataTable.
 *
 * @param {google.visualization.DataTable} data the DataTable corresponding to
 *     the gender ratio data
 *
 * @param {JSON} genderRatioInfo the JSON object corresponding to the gender
 *     ratio data that will be graphed.
 */
function populateGenderRatioDataTable(data, genderRatioInfo) {
  data.addColumn('string', 'Your Colleges');
  data.addColumn('number', 'Ratio of Men (%)');
  data.addColumn('number', 'Ratio of Women (%)');
  genderRatioInfo.forEach((college) => {
    data.addRow(
        [college['name'], college['ratioOfMen'], college['ratioOfWomen']],
    );
  });
}
