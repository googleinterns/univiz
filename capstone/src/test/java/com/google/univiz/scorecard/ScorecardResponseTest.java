package com.google.univiz.scorecard;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.io.InputStreamReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class ScorecardResponseTest {

  /**
   * JSON from scorecard.json comes directly from the CollegeScorecard API.
   *
   * @see <a
   *     href="https://api.data.gov/ed/collegescorecard/v1/schools.json?school.name=New%20York%20University&per_page=1&fields=id,school.name,school.city,school.main_campus,location.lat,location.lon,school.carnegie_size_setting,latest.admissions.admission_rate.overall,latest.admissions.sat_scores.average.overall,latest.student.size,latest.cost.attendance.academic_year,latest.student.demographics.men,latest.student.demographics.women&api_key=YOUR-API-KEY">http://api.data.gov/ed/collegescorecard</a>
   */
  @Test
  public void testJsonDeserializes() throws Exception {
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY).create();
    InputStreamReader scorecardReader =
        new InputStreamReader(
            Resources.getResource(ScorecardResponseTest.class, "scorecard_response.json")
                .openStream());
    ScorecardResponse response = gson.fromJson(scorecardReader, ScorecardResponse.class);
    assertThat(response.scorecardData()).hasSize(1);
    ScorecardData scorecardData = response.scorecardData().get(0);

    assertThat(scorecardData.name()).isEqualTo("New York University");
  }
}
