google.maps = {};
google.maps.Map = class {
  /**
    * @param {HTMLElement} div Div from HTML that map will be displayed in
    * @param {Object} options Options for centering and viewing maps in HTML
    */
  constructor(div, options) {};
};

google.maps.LatLng = class {
  /**
    * @param {float} lat Location measured in latitude
    * @param {float} lng Location measured in longitude
    */
  constructor(lat, lng) {};
};

google.maps.Marker = class {
  /**
    * @param {google.maps.LatLon} latLon google.maps.LatLon object
    * @param {google.maps.Map} map google.maps.Map object
    * @param {string} title Name of college/university/institution
    */
  constructor(latLon, map, title) {};
};

google.maps.InfoWindow = class {
  /** Creates an info window */
  constructor() {};

  /**
    * @param {string} description Description text displayed
    *                             when marker is clicked
    */
  setContent(description) {}

  /**
   * @param {google.maps.Map} map Map reference to display window
   * @param {google.maps.Marker} marker Marker reference to
   *                                    window
   */
  open(map, marker) {}
};

google.maps.event = {};
/**
   * @param {google.maps.Marker} marker Marker reference to
   *                                    info window
   * @param {string} event Event that google.maps.event
                           will listen for
   * @param {Function} handler Function that will create 
                               description for info window
   */
google.maps.event.addListener = (marker, event, handler) => {};

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
  beforeEach(() => {
    spyOn(google.maps.Map.prototype, 'constructor');
    spyOn(google.maps.LatLng.prototype, 'constructor');
    spyOn(google.maps.Marker.prototype, 'constructor');
    spyOn(google.maps.InfoWindow.prototype, 'constructor');
    spyOn(google.maps.InfoWindow.prototype, 'setContent');
    spyOn(google.maps.InfoWindow.prototype, 'open');
  });
  it('will add map content using data from JSON',
      async () => {
        spyOn(window, 'fetch')
            .and.returnValue(Promise.resolve({json: () => mapsJsonData}));
        await fetchData();
        expect(fetchData.calls.count()).toEqual(1);
        createMap(mapsJsonData);
        expect(google.maps.Map.prototype.constructor.calls.count()).toEqual(1);
        expect(google.maps.LatLng.prototype.constructor).toHaveBeenCalledWith([
          nyuLat,
          nyuLon,
        ]);
        expect(google.maps.LatLng.prototype.constructor).toHaveBeenCalledWith([
          stanLat,
          stanLon,
        ]);
        expect(google.maps.LatLng.prototype.constructor.calls
            .count()).toEqual(2);
        expect(google.maps.Marker.prototype.constructor.calls
            .count()).toEqual(2);
        expect(google.maps.InfoWindow.prototype.constructor.calls
            .count()).toEqual(1);
      });
});
