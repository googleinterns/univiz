
describe('Map Marker and Info Window Content', () => {
  it('will add markers and info window content with data from JSON', async () =>
    const mapsJsonData = [{'latitude': 40.729452,
      'longitude': -73.997264, 'name': 'New York University',
      'city': 'New York', 'isMainCampus': true},
      {'latitude': 37.429434, 'longitude': -122.167359,
      'name': 'Stanford University','city': 'Stanford', 
      'isMainCampus': true},
    ];
    spyOn(window, 'fetch')
      .and.returnValue(Promise.resolve({json: () => mapsJsonData}));
    await fetchData();
    )
})