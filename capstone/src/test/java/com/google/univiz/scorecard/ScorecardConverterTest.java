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

  @Test
  public void converterTest() throws Exception {
    ScorecardData.Builder scorecardBuilder = ScorecardData.builder();
    ScorecardData scorecardData =
        scorecardBuilder
            .setId(193900)
            .setName("New York University")
            .setCity("New York")
            .setFlagMainCampus(1)
            .setLatitude(40.729452)
            .setLongitude(-73.997264)
            .setCarnegieSizeDegree(17)
            .setAdmissionRate(0.1999)
            .setAvgSat(1419.0)
            .setNumOfUndergrads(26339)
            .setAvgCost(69830)
            .setRatioOfMen(0.4253)
            .setRatioOfWomen(0.5747)
            .build();

    Converter<ScorecardData, CollegeData> converter = new ScorecardConverter();
    CollegeData college = converter.convert(scorecardData);

    assertThat(college.id().id()).isEqualTo(193900);
    assertThat(college.name()).isEqualTo("New York University");
    assertThat(college.city()).isEqualTo("New York");
    assertThat(college.isMainCampus()).isEqualTo(true);
    assertThat(college.latitude()).isEqualTo(40.729452);
    assertThat(college.longitude()).isEqualTo(-73.997264);
    assertThat(college.carnegieSizeDegree()).isEqualTo(CarnegieSizeDegree.getDegree(17));
    assertThat(college.admissionRate()).isEqualTo(0.1999);
    assertThat(college.avgSat()).isEqualTo(1419.0);
    assertThat(college.numOfUndergrads()).isEqualTo(26339);
    assertThat(college.avgCost()).isEqualTo(69830);
    assertThat(college.ratioOfMen()).isEqualTo(0.4253);
    assertThat(college.ratioOfWomen()).isEqualTo(0.5747);
  }
}
