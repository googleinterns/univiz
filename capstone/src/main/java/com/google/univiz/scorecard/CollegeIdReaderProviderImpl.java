package com.google.univiz.scorecard;

import com.google.univiz.api.CollegeId;
import com.google.univiz.config.UnivizConfig;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.inject.Inject;

class CollegeIdReaderProviderImpl implements CollegeIdReaderProvider {
  private final UnivizConfig univizConfig;

  @Inject
  public CollegeIdReaderProviderImpl(UnivizConfig univizConfig) {
    this.univizConfig = univizConfig;
  }

  @Override
  public Reader getReaderFromCollegeId(CollegeId id) {
    String urlString = "https://api.data.gov/ed/collegescorecard/v1/schools.json?id=";
    urlString += Integer.toString(id.id());
    urlString += "&per_page=1&fields=id,school.name,school.city,school.main_";
    urlString += "campus,location.lat,location.lon,school.carnegie_size_setting,";
    urlString += "latest.admissions.admission_rate.overall,";
    urlString += "latest.admissions.sat_scores.average.overall,latest.student.size,";
    urlString += "latest.cost.attendance.academic_year,latest.student.demographics.men,";
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
      is =
          new InputStream() {
            @Override
            public int read() {
              return 0;
            }
          };
    } catch (IOException e) {
      // log this
      // then create empty stream
      is =
          new InputStream() {
            @Override
            public int read() {
              return 0;
            }
          };
    }
    return new InputStreamReader(is);
  }
}
