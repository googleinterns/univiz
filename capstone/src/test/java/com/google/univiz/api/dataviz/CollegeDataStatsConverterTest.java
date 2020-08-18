package com.google.univiz.api.dataviz;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.univiz.CarnegieSizeDegree;
import com.google.univiz.CollegeData;
import com.google.univiz.CollegeId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class CollegeDataStatsConverterTest {

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

  private final CollegeDataStatsConverter converter = new CollegeDataStatsConverter();

  @Test
  public void convertToCollegeStats() {
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

    CollegeStats stats = converter.convert(college);

    assertThat(stats.admissionRate()).isEqualTo(ADMISSION_RATE);
    assertThat(stats.avgSat()).isEqualTo(AVG_SAT);
    assertThat(stats.numOfUndergrads()).isEqualTo(NUM_OF_UNDERGRADS);
    assertThat(stats.avgCost()).isEqualTo(AVG_COST);
    assertThat(stats.ratioOfMen()).isEqualTo(RATIO_OF_MEN);
    assertThat(stats.ratioOfWomen()).isEqualTo(RATIO_OF_WOMEN);
  }

  @Test
  public void convertBackToCollegeData() {
    CollegeStats stats =
        CollegeStats.builder()
            .setAdmissionRate(ADMISSION_RATE)
            .setAvgSat(AVG_SAT)
            .setNumOfUndergrads(NUM_OF_UNDERGRADS)
            .setAvgCost(AVG_COST)
            .setRatioOfMen(RATIO_OF_MEN)
            .setRatioOfWomen(RATIO_OF_WOMEN)
            .build();

    assertThrows(UnsupportedOperationException.class, () -> converter.reverse().convert(stats));
  }
}
