

function createMap() {
  const map = new google.maps.Map(
    document.getElementById('map'),
    {center: {lat: 34.052, lng: -118.245}, zoom: 6});

  const mapsDataList = fetchData();
  
  for (const mapsData of mapsDataList) {
    var latitudeLongitude = new google.maps.LatLng(mapsData['latitude'], mapsData['longitude']);

    var marker = new google.maps.Marker({
      position: latitudeLongitude,
      map: map,
      title: mapsData['name']
    });
  }
};

/**
 * Helper function to fetch data from servlet as JSON.
 *
 * @return {type?} mapsData
 */
async function fetchData() {
  await fetch('/map').then(response => response.json()).then((mapsDataFromJSON) => {
    console.log('Fetching data...');
    console.log(mapsDataFromJSON);
  return mapsDataFromJSON;
  }); 
}