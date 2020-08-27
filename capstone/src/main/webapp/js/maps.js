/* eslint no-unused-vars: ["error", {"varsIgnorePattern": "fetchData"}] */

/**
 * Function to fetch data from servlet as JSON and calls createMap().
 */
async function fetchData() {
  await fetch('/maps')
      .then((response) => response.json())
      .then((mapsJsonData) => {
        console.log('Fetching data...');
        console.log(mapsJsonData);
        createMap(mapsJsonData);
      });
}

/**
 * Function that creates map markers and info windows.
 * @param {JSON} mapsDataFromFetch JSON response
 */
function createMap(mapsDataFromFetch) {
  const map = new google.maps.Map(
      document.getElementById('map'),
      {center: {lat: 39.8097343, lng: -98.5556199}, zoom: 5});

  const infoWindow = new google.maps.InfoWindow();
  for (const mapsData of mapsDataFromFetch) {
    const latitudeLongitude = new google.maps.LatLng(
        mapsData['latitude'],
        mapsData['longitude']);

    const marker = new google.maps.Marker({
      position: latitudeLongitude,
      map: map,
      title: mapsData['name'],
    });

    ((marker, mapsData) => {
      google.maps.event.addListener(marker, 'click',
          () => {
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
 * Helper function for setting content description for info windows.
 * @param {string} name Name of college/university/institution
 * @param {string} city City where college/university/institution is located in
 * @param {boolean} isMainCampus True if it is the main campus, false otherwise
 * @return {string} Description that will be used for info window content
 */
function setDescription(name, city, isMainCampus) {
  const description = name + ' is located in ' + city + '. ';
  if (isMainCampus) {
    return description + 'This is the main campus.';
  } else {
    return description + 'This is not the main campus.';
  }
}
