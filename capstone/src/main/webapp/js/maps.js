/**
 * Displays the map onto the dashboard with hardcoded values.
 * @returns {void}
 */
function createMap() {
  const map = new google.maps.Map(
      document.getElementById('map'),
      {center: {lat: 34.052, lng: -118.245}, zoom: 10});
}
