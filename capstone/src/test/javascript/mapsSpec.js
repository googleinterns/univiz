google.maps = {};
google.maps.Map = class {
  /**
    * @param {HTMLElement} div Div from HTML that map will be displayed in
    * @param {Object} options Options for centering and viewing maps in HTML
    */
  emptyMethod(div, options) {};
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
  emptyMethod(lat, lng) {};
  /**
    * @param {float} lat Location measured in latitude
    * @param {float} lng Location measured in longitude
    */
  constructor(lat, lng) {
    this.emptyMethod(lat, lng);
  };
};

google.maps.Marker = class {
  /**
    * @param {google.maps.LatLon} latLon google.maps.LatLon object
    * @param {google.maps.Map} map google.maps.Map object
    * @param {string} title Name of college/university/institution
    */
  emptyMethod(latLon, map, title) {};
  /**
    * @param {google.maps.LatLon} latLon google.maps.LatLon object
    * @param {google.maps.Map} map google.maps.Map object
    * @param {string} title Name of college/university/institution
    */
  constructor(latLon, map, title) {};
};

google.maps.InfoWindow = class {
  /** Creates an info window */
  emptyMethod() {};
  /** Creates an info window */
  constructor() {};

  /**
    * @param {string} description Description text displayed
    * when marker is clicked
    */
  setContent(description) {}

  /**
   * @param {google.maps.Map} map Map reference to display window
   * @param {google.maps.Marker} marker Marker reference to
   * window
   */
  open(map, marker) {}
};

google.maps.event = {};
/**
   * @param {google.maps.Marker} marker Marker reference to
   *                                    info window
   * @param {string} event Event that google.maps.event
   * will listen for
   * @param {Function} handler Function that will create
   * description for info window
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
  const nyuDescription = mapsJsonData[0]['name'] +
  ' is located in ' + mapsJsonData[0]['city'] +
   '. This is the main campus.';
  const stanLat = mapsJsonData[1]['latitude'];
  const stanLon = mapsJsonData[1]['longitude'];
  const stanDescription = mapsJsonData[1]['name'] +
  ' is located in ' + mapsJsonData[1]['city'] +
   '. This is the main campus.';
  beforeEach(() => {
    spyOn(google.maps.Map.prototype, 'emptyMethod');
    spyOn(google.maps.LatLng.prototype, 'emptyMethod');
    spyOn(google.maps.Marker.prototype, 'emptyMethod');
    spyOn(google.maps.InfoWindow.prototype, 'emptyMethod');
    spyOn(google.maps.InfoWindow.prototype, 'setContent');
    spyOn(google.maps.InfoWindow.prototype, 'open');
  });
  it('will add map content using data from JSON',
      async () => {
        spyOn(window, 'fetch')
            .and.returnValue(Promise.resolve({json: () => mapsJsonData}));
        await fetchData();
        createMap(mapsJsonData);
        expect(google.maps.LatLng.prototype.emptyMethod).toHaveBeenCalledWith(
            nyuLat,
            nyuLon,
        );
        expect(google.maps.LatLng.prototype.emptyMethod).toHaveBeenCalledWith(
            stanLat,
            stanLon,
        );
        expect(google.maps.Marker.prototype.emptyMethod.calls
            .count()).toEqual(2);
        expect(google.maps.InfoWindow.prototype.emptyMethod.calls
            .count()).toEqual(1);
        expect(google.maps.InfoWindow.prototype.setContent)
            .toHaveBeenCalledWith(nyuDescription);
        expect(google.maps.InfoWindow.prototype.setContent)
            .toHaveBeenCalledWith(stanDescription);
      });
  it('will create descriptions for info windows', () => {
    expect(setDescription(
        mapsJsonData[0]['name'],
        mapsJsonData[0]['city'],
        mapsJsonData[0]['isMainCampus']))
        .toEqual(nyuDescription);
    expect(setDescription(
        mapsJsonData[1]['name'],
        mapsJsonData[1]['city'],
        mapsJsonData[1]['isMainCampus']))
        .toEqual(stanDescription);
  });
});
