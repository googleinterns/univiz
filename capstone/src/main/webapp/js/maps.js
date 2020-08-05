/**
 * createMap() just displays the map onto the dashboard.
 * All parameters for positioning are hardcoded.
 */

function createMap() {
  const map = new google.maps.Map(
    document.getElementById('map'),
    {center: {lat: 34.052, lng: -118.245}, zoom: 10});
}