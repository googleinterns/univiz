package com.google.univiz.scorecard;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.base.Converter;
import com.google.univiz.CarnegieSizeDegree;
import com.google.univiz.CollegeData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class ScorecardConverterTest {

  private static final int COLLEGE_ID = 193900;
  private static final String NAME = "New York University";
  private static final String CITY = "New York";
  private static final int FLAG_MAIN_CAMPUS = 1;
  private static final double LATITUDE = 40.729452;
  private static final double LONGITUDE = -73.997264;
  private static final int CARNEGIE_SIZE_DEGREE = 17;
  private static final double ADMISSION_RATE = 0.1999;
  private static final double AVG_SAT = 1419.0;
  private static final int NUM_OF_UNDERGRADS = 26339;
  private static final int AVG_COST = 69830;
  private static final double RATIO_OF_MEN = 0.4253;
  private static final double RATIO_OF_WOMEN = 0.5747;

  @Test
  public void convertToCollegeData() throws Exception {
    ScorecardData scorecardData =
        ScorecardData.builder()
            .setId(COLLEGE_ID)
            .setName(NAME)
            .setCity(CITY)
            .setFlagMainCampus(FLAG_MAIN_CAMPUS)
            .setLatitude(LATITUDE)
            .setLongitude(LONGITUDE)
            .setCarnegieSizeDegree(CARNEGIE_SIZE_DEGREE)
            .setAdmissionRate(ADMISSION_RATE)
            .setAvgSat(AVG_SAT)
            .setNumOfUndergrads(NUM_OF_UNDERGRADS)
            .setAvgCost(AVG_COST)
            .setRatioOfMen(RATIO_OF_MEN)
            .setRatioOfWomen(RATIO_OF_WOMEN)
            .build();

    Converter<ScorecardData, CollegeData> converter = new ScorecardConverter();
    CollegeData college = converter.convert(scorecardData);

    assertThat(college.id().id()).isEqualTo(COLLEGE_ID);
    assertThat(college.name()).isEqualTo(NAME);
    assertThat(college.city()).isEqualTo(CITY);
    assertThat(college.isMainCampus()).isEqualTo(true);
    assertThat(college.latitude()).isEqualTo(LATITUDE);
    assertThat(college.longitude()).isEqualTo(LONGITUDE);
    assertThat(college.carnegieSizeDegree()).isEqualTo(CarnegieSizeDegree.getDegree(17));
    assertThat(college.admissionRate()).isEqualTo(ADMISSION_RATE);
    assertThat(college.avgSat()).isEqualTo(AVG_SAT);
    assertThat(college.numOfUndergrads()).isEqualTo(NUM_OF_UNDERGRADS);
    assertThat(college.avgCost()).isEqualTo(AVG_COST);
    assertThat(college.ratioOfMen()).isEqualTo(RATIO_OF_MEN);
    assertThat(college.ratioOfWomen()).isEqualTo(RATIO_OF_WOMEN);
  }
}
