const google = {};
google.maps = class {
  /**
    * Constructing google.maps for testing purposes
    */
  constructor() {}

  /**
    *
    * @param {HTMLElement} div Div from HTML that map will be displayed in
    * @param {Object} options Options for centering and viewing maps in HTML
    */
  Map(div, options) {}

  /**
    * @param {float} lat Location measured in latitude
    * @param {float} lon Location measured in longitude
    */
  LatLon(lat, lon) {}

  /**
    * @param {google.maps.LatLon} latLon google.maps.LatLon object
    * @param {google.maps.Map} map google.maps.Map object
    * @param {string} title Name of college/university/institution
    */
  Marker(latLon, map, title) {}

  /**
    * Creates InfoWindow on map
    */
  InfoWindow() {}
};

describe('Map Marker and InfoWindow Display', () => {
  const mapsJsonData = [
    {'name': 'New York University',
      'city': 'New York',
      'isMainCampus': true,
      'latitude': 40.729452,
      'longitude': -73.997264},
    {'name': 'Stanford University',
      'city': 'Stanford',
      'isMainCampus': true,
      'latitude': 37.429434,
      'longitude': -122.167359},
  ];
  const nyuLat = mapsJsonData[0]['latitude'];
  const nyuLon = mapsJsonData[0]['longitude'];
  const stanLat = mapsJsonData[1]['latitude'];
  const stanLon = mapsJsonData[1]['longitude'];
  it('will add map content using data from JSON',
      async () => {
        spyOn(window, 'createMap');
        spyOn(google.maps.prototype, 'constructor');
        spyOn(window, 'fetchData')
            .and.returnValue(Promise.resolve({json: () => mapsJsonData}));
        await fetchData();
        spyOn(google.maps.prototype, 'LatLon');
        spyOn(google.maps.prototype, 'Marker');
        spyOn(google.maps.prototype, 'InfoWindow');
        spyOn(window, 'setDescription');
        expect(google.maps.prototype.Map.calls.count()).toEqual(1);
        expect(google.maps.prototype.LatLon).toHaveBeenCalledWith([
          nyuLat,
          nyuLon,
        ]);d
        expect(google.maps.prototype.LatLon).toHaveBeenCalledWith([
          stanLat,
          stanLon,
        ]);
        expect(google.maps.prototype.LatLon.calls.count()).toEqual(2);
        expect(google.maps.prototype.Marker.calls.count()).toEqual(2);
        expect(google.maps.prototype.InfoWindow.calls.count()).toEqual(1);
        expect(setDescription).toHaveBeenCalledWith([
          mapsJsonData[0]['name'],
          mapsJsonData[0]['city'],
          mapsJsonData[0]['isMainCampus'],
        ]);
        expect(setDescription).toHaveBeenCalledWith([
          mapsJsonData[1]['name'],
          mapsJsonData[1]['city'],
          mapsJsonData[1]['isMainCampus'],
        ]);
        expect(setDescription.calls.count()).toEqual(2);
      });
});
