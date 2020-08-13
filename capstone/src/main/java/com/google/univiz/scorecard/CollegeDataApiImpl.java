package com.google.univiz.scorecard;

import com.google.gson.Gson;
import com.google.univiz.config.UnivizConfig;
import com.google.univiz.CollegeData;
import com.google.univiz.CollegeDataApi;
import com.google.univiz.api.CollegeId;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class CollegeDataApiImpl implements CollegeDataApi {
  private final UnivizConfig univizConfig;
  private final ScorecardConverter scorecardConverter;

  @Inject
  public CollegeDataApiImpl(UnivizConfig univizConfig, ScorecardConverter scorecardConverter) {
    this.univizConfig = univizConfig;
    this.scorecardConverter = scorecardConverter;
  }

  @Override
  public List<CollegeData> getCollegesById(List<CollegeId> ids) {
    List<CollegeData> colleges = new ArrayList<>();
    for (CollegeId id: ids) {
      // Then deserialize into a ScorecardResponse
      Gson gson = new Gson();
      // ScorecardResponse scorecardResponse =
      //   gson.fromJson(getReaderFromCollegeId(id), ScorecardResponse.class);
      // ScorecardData scorecard = response.scorecardData().get(0);
      // Then convert to a CollegeData
      // CollegeData college = scorecardConverter.doForward(scorecard);
      // colleges.add(college);
    }
    return colleges;
  }

  protected Reader getReaderFromCollegeId(CollegeId id) {
    String urlString =
      "https://api.data.gov/ed/collegescorecard/v1/schools.json?id=";
    // urlString += (String)id.id();
    urlString += "&per_page=1&fields=id,school.name,school.city,school.main_";
    urlString +=
      "campus,location.lat,location.lon,school.carnegie_size_setting,";
    urlString += "latest.admissions.admission_rate.overall,";
    urlString +=
      "latest.admissions.sat_scores.average.overall,latest.student.size,";
    urlString +=
      "latest.cost.attendance.academic_year,latest.student.demographics.men,";
    urlString += "latest.student.demographics.women&api_key=";
    urlString += univizConfig.scorecardApiKey();

    // Make REST call
    InputStream is;
    try {
      URL url = new URL(urlString);
      is = url.openStream();
    } catch (MalformedURLException e) {
      // log this
      // then create empty stream
      is = new InputStream() {
        @Override
        public int read() {
          return 0;
        }
      };
    } catch (IOException e) {
      // log this
      // then create empty stream
      is = new InputStream() {
        @Override
        public int read() {
          return 0;
        }
      };
    }
    return new InputStreamReader(is);
  }
}
