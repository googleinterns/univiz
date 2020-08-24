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
        document.getElementById('admissionRateData'),
    );
  const avgSatChart =
    new google.visualization.ColumnChart(document.getElementById('avgSatData'));
  const numUndergradsChart =
    new google.visualization.ColumnChart(
        document.getElementById('numUndergradsData'),
    );
  const genderRatioChart =
    new google.visualization.ColumnChart(
        document.getElementById('genderRatioData'),
    );
  deserializeGenderRatioData().then(
      (data) => genderRatioChart.draw(data, genderRatioOptions),
  );
  deserializeAdmissionData().then(
      (data) => admissionRateChart.draw(data, admissionRateOptions),
  );
  deserializeAvgSatData().then(
      (data) => avgSatChart.draw(data, avgSatOptions),
  );
  deserializeNumUndergradsData().then(
      (data) => numUndergradsChart.draw(data, numUndergradsOptions),
  );
}

/**
 * Helper function to fetch elements from JSON servlet.
 *
 * @return {google.visualization.DataTable} data the DataTable corresponding to
 *     the admission rate data
 */
async function deserializeAdmissionData() {
  const data = new google.visualization.DataTable();
  await fetch('/viz/stats')
      .then((response) => response.json())
      .then(
          (admissionInfo) =>
            populateAdmissionRateDataTable(data, admissionInfo),
      );
  return data;
}

/**
 * Helper function to fetch elements from JSON servlet.
 *
 * @return {google.visualization.DataTable} data the DataTable corresponding to
 *     the average SAT score data
 */
async function deserializeAvgSatData() {
  const data = new google.visualization.DataTable();
  await fetch('/viz/stats')
      .then((response) => response.json())
      .then((avgSatInfo) => populateAvgSatDataTable(data, avgSatInfo));
  return data;
}

/**
 * Helper function to fetch elements from JSON servlet.
 *
 * @return {google.visualization.DataTable} data the DataTable corresponding to
 *     the data on the number of undergraduate students per college
 */
async function deserializeNumUndergradsData() {
  const data = new google.visualization.DataTable();
  await fetch('/viz/stats')
      .then((response) => response.json())
      .then(
          (numUndergradsInfo) =>
            populateNumUndergradsDataTable(data, numUndergradsInfo),
      );
  return data;
}

/**
 * Helper function to fetch elements from JSON servlet.
 *
 * @return {google.visualization.DataTable} data the DataTable corresponding to
 *     the gender ratio data
 */
async function deserializeGenderRatioData() {
  const data = new google.visualization.DataTable();
  await fetch('/viz/stats')
      .then((response) => response.json())
      .then(
          (genderRatioInfo) =>
            populateGenderRatioDataTable(data, genderRatioInfo),
      );
  return data;
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
