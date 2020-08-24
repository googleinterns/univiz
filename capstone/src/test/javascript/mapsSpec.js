
describe('Map Marker and InfoWindow Display', () => {
  it('will add map content using data from JSON',
      async () => {
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
        spyOn(window, 'fetch')
            .and.returnValue(Promise.resolve({json: () => mapsJsonData}));
        await fetchData();
      });
});
