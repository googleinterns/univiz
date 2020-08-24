/* eslint no-unused-vars: ["error", {"varsIgnorePattern": "createMap"}] */

/**
 * Function that creates map markers and info windows.
 */
function createMap() {
  const map = new google.maps.Map(
      document.getElementById('map'),
      {center: {lat: 39.8097343, lng: -98.5556199}, zoom: 5});

  const mapsDataList = fetchData();

  const infoWindow = new google.maps.InfoWindow();
  for (const mapsData of mapsDataList) {
    const latitudeLongitude = new google.maps.LatLng(
        mapsData['latitude'],
        mapsData['longitude']);

    const marker = new google.maps.Marker({
      position: latitudeLongitude,
      map: map,
      title: mapsData['name'],
    });

    (function(marker, mapsData) {
      google.maps.event.addListener(marker, 'click',
          function(e) {
            infoWindow.setContent(setDescription(
                mapsData['name'],
                mapsData['city'],
                mapsData['isMainCampus']));
            infoWindow.open(map, marker);
          });
    })(marker, mapsData);
  }
}

/**
 * Helper function to fetch data from servlet as JSON.
 *
 * @return {JSON} an array of dictionaries corresponding to relevant map data
 */
async function fetchData() {
  await fetch('/map')
      .then((response) => response.json())
      .then((mapsDataFromJSON) => {
        console.log('Fetching data...');
        console.log(mapsDataFromJSON);
      });
  return mapsDataFromJSON;
}

/**
 * Helper function for setting content description for info windows.
 * @param {string} name Name of college/university/institution
 * @param {string} city City where college/university/institution is located in
 * @param {boolean} isMainCampus True if it is the main campus, false otherwise
 * @return {string} Description that will be used for info window content
 */
function setDescription(name, city, isMainCampus) {
  const description = name + ' is located in ' + city + '. ';
  if (isMainCampus == true) {
    return description + 'This is the main campus.';
  } else {
    return description + 'This is not the main campus.';
  }
}

// json = [{'latitude': 40.729452,
// 'longitude': -73.997264, 'name': 'New York University',
//     'city': 'New York', 'isMainCampus': true},
// {'latitude': 37.429434, 'longitude': -122.167359,
//     'name': 'Stanford University',
// 'city': 'Stanford', 'isMainCampus': true}];
