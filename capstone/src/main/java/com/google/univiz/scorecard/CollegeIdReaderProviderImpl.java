package com.google.univiz.scorecard;

import com.google.univiz.api.CollegeId;
import com.google.univiz.config.UnivizConfig;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.inject.Inject;

class CollegeIdReaderProviderImpl implements CollegeIdReaderProvider {
  private final UnivizConfig univizConfig;

  @Inject
  protected CollegeIdReaderProviderImpl(UnivizConfig univizConfig) {
    this.univizConfig = univizConfig;
  }

  @Override
  public Reader getReaderFromCollegeIds(List<CollegeId> ids) throws IOException {
    String urlString = "https://api.data.gov/ed/collegescorecard/v1/schools.json?id=";
    for (CollegeId id : ids) {
      urlString += Integer.toString(id.id());
      urlString += ",";
    }
    // chop off extra comma
    urlString = urlString.substring(0, urlString.length() - 1);
    urlString += "&per_page=1&fields=id,school.name,school.city,school.main_";
    urlString += "campus,location.lat,location.lon,school.carnegie_size_setting,";
    urlString += "latest.admissions.admission_rate.overall,";
    urlString += "latest.admissions.sat_scores.average.overall,latest.student.size,";
    urlString += "latest.cost.attendance.academic_year,latest.student.demographics.men,";
    urlString += "latest.student.demographics.women&api_key=";
    urlString += univizConfig.scorecardApiKey();

    // Make REST call
    return new InputStreamReader(getStreamFromUrl(urlString));
  }

  public InputStream getStreamFromUrl(String urlString) throws IOException {
    InputStream is;
    try {
      URL url = new URL(urlString);
      is = url.openStream();
      return is;
    } catch (MalformedURLException e) {
      throw new AssertionError(e);
    }
  }
}
