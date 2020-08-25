const google = {};
google.maps = {};
google.maps.Map = class {
  /**
  * Constructor for google.maps.Map class
  *
  * @param {HTMLElement} div Div from html that map will be displayed in
  * @param {center: Object.<{lat: double, lon: double}>, zoom: integer>} options
  Options for centering and viewing maps in HTML
  */
  constructor(div, options) {
    this.div = div;
    this.options = options;
  }
};
google.maps.LatLng = class {
  constructor(lat, lon) {
    this.lat = lat;
    this.lon = lon;
  }
};
google.maps.Marker = class {
  constructor(latLon, map, title) {
    this.latLon = latLon;
    this.map = map;
    this.title = title;
  }
};
google.maps.InfoWindow = class {
  constructor() {}
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
        spyOn(google.maps, 'Map');
        spyOn(window, 'fetchData')
            .and.returnValue(Promise.resolve({json: () => mapsJsonData}));
        await fetchData();
        spyOn(google.maps, 'LatLon');
        spyOn(google.maps, 'Marker');
        spyOn(google.maps, 'InfoWindow');
        spyOn(window, 'setDescription');
        expect(google.maps.Map.calls.count()).toEqual(1);
        expect(google.maps.LatLon).toHaveBeenCalledWith([
          nyuLat,
          nyuLon,
        ]);
        expect(google.maps.LatLon).toHaveBeenCalledWith([
          stanLat,
          stanLon,
        ]);
        expect(google.maps.LatLon.calls.count()).toEqual(2);
        expect(google.maps.Marker.calls.count()).toEqual(2);
        expect(google.maps.InfoWindow.calls.count()).toEqual(1);
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
