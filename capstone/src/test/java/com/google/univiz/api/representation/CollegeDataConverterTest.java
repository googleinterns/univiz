package com.google.univiz.api.representation;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class CollegeDataConverterTest {

  private static final int COLLEGE_ID = 193900;
  private static final String NAME = "New York University";
  private static final String CITY = "New York";
  private static final boolean FLAG_MAIN_CAMPUS = true;
  private static final double LATITUDE = 40.729452;
  private static final double LONGITUDE = -73.997264;
  private static final int CARNEGIE_SIZE_DEGREE = 17;
  private static final double ADMISSION_RATE = 0.1999;
  private static final double AVG_SAT = 1419.0;
  private static final int NUM_OF_UNDERGRADS = 26339;
  private static final int AVG_COST = 69830;
  private static final double RATIO_OF_MEN = 0.4253;
  private static final double RATIO_OF_WOMEN = 0.5747;

  private final CollegeDataConverter converter = new CollegeDataConverter();

  @Test
  public void convertToMapsData() throws Exception {
    CollegeData college =
        CollegeData.builder()
            .setId(CollegeId.create(COLLEGE_ID))
            .setName(NAME)
            .setCity(CITY)
            .setIsMainCampus(FLAG_MAIN_CAMPUS)
            .setLatitude(LATITUDE)
            .setLongitude(LONGITUDE)
            .setCarnegieSizeDegree(CarnegieSizeDegree.getDegree(CARNEGIE_SIZE_DEGREE))
            .setAdmissionRate(ADMISSION_RATE)
            .setAvgSat(AVG_SAT)
            .setNumOfUndergrads(NUM_OF_UNDERGRADS)
            .setAvgCost(AVG_COST)
            .setRatioOfMen(RATIO_OF_MEN)
            .setRatioOfWomen(RATIO_OF_WOMEN)
            .build();

    MapsData mapData = converter.convert(college);

    assertThat(mapData.name()).isEqualTo(NAME);
    assertThat(mapData.city()).isEqualTo(CITY);
    assertThat(mapData.isMainCampus()).isEqualTo(FLAG_MAIN_CAMPUS);
    assertThat(mapData.latitude()).isEqualTo(LATITUDE);
    assertThat(mapData.longitude()).isEqualTo(LONGITUDE);
  }

  @Test
  public void convertBackToCollegeData() {
    MapsData mapData =
        MapsData.builder()
            .setName(NAME)
            .setCity(CITY)
            .setIsMainCampus(FLAG_MAIN_CAMPUS)
            .setLatitude(LATITUDE)
            .setLongitude(LONGITUDE)
            .build();

    assertThrows(UnsupportedOperationException.class, () -> converter.reverse().convert(mapData));
  }
}
