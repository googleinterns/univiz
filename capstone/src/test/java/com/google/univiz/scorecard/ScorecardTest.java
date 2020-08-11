package com.google.univiz.scorecard;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.base.Converter;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.univiz.CollegeData;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.io.InputStreamReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class ScorecardTest {

  /**
   * JSON from scorecard.json comes directly from the CollegeScorecard API.
   * https://api.data.gov/ed/collegescorecard/v1/schools.json?school.name=New%20
   * York%20University&per_page=1&fields=id,school.name,school.city,school.main_
   * campus,school.degree_urbanization,location.lat,location.lon,school.carnegie
   * _size_setting,latest.admissions.admission_rate.overall,latest.admissions.sat
   * _scores.average.overall,latest.student.size,latest.cost.attendance.academic_
   * year,latest.student.demographics.men,latest.student.demographics.women&api_key=YOUR-API-KEY
   */
  @Test
  public void testJsonDeserializes() throws Exception {
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY).create();
    InputStreamReader scorecardReader =
        new InputStreamReader(
            Resources.getResource(ScorecardTest.class, "scorecard.json").openStream());
    ScorecardData scorecardData = gson.fromJson(scorecardReader, ScorecardData.class);

    assertThat(scorecardData.name()).isEqualTo("New York University");
    assertThat(scorecardData.city()).isEqualTo("New York");
    assertThat(scorecardData.flagMainCampus()).isEqualTo(1);
    assertThat(scorecardData.latitude()).isEqualTo(40.729452);
    assertThat(scorecardData.longitude()).isEqualTo(-73.997264);
    assertThat(scorecardData.carnegieSizeDegree()).isEqualTo(17);
    assertThat(scorecardData.admissionRate()).isEqualTo(0.1999);
    assertThat(scorecardData.avgSat()).isEqualTo(1419.0);
    assertThat(scorecardData.numOfUndergrads()).isEqualTo(26339);
    assertThat(scorecardData.avgCost()).isEqualTo(69830);
    assertThat(scorecardData.ratioOfMen()).isEqualTo(0.4253);
    assertThat(scorecardData.ratioOfWomen()).isEqualTo(0.5747);
  }

  @Test
  public void converterTest() throws Exception {
    // TODO(biancamacias): finish test
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY).create();
    InputStreamReader scorecardReader =
        new InputStreamReader(
            Resources.getResource(ScorecardTest.class, "scorecard.json").openStream());
    ScorecardData scorecardData = gson.fromJson(scorecardReader, ScorecardData.class);

    Converter<ScorecardData, CollegeData> converter = new ScorecardConverter();
    CollegeData college = converter.convert(scorecardData);

    assertThat(college.name()).isEqualTo("New York University");
    assertThat(college.city()).isEqualTo("New York");
    assertThat(college.latitude()).isEqualTo(40.729452);
    assertThat(college.longitude()).isEqualTo(-73.997264);
    assertThat(college.admissionRate()).isEqualTo(0.1999);
    assertThat(college.avgSat()).isEqualTo(1419.0);
    assertThat(college.numOfUndergrads()).isEqualTo(26339);
    assertThat(college.avgCost()).isEqualTo(69830);
    assertThat(college.ratioOfMen()).isEqualTo(0.4253);
    assertThat(college.ratioOfWomen()).isEqualTo(0.5747);
  }
}
