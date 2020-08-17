package com.google.univiz.scorecard;

import static java.util.stream.Collectors.joining;

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
    final StringBuilder urlStringBuilder = new StringBuilder();
    urlStringBuilder.append("https://api.data.gov/ed/collegescorecard/v1/schools.json?id=");
    String stringWithIds = ids.stream().map(id -> Integer.toString(id.id())).collect(joining(","));
    urlStringBuilder.append(stringWithIds);
    urlStringBuilder.append("&per_page=1&fields=id,school.name,school.city,school.main_");
    urlStringBuilder.append("campus,location.lat,location.lon,school.carnegie_size_setting,");
    urlStringBuilder.append("latest.admissions.admission_rate.overall,");
    urlStringBuilder.append("latest.admissions.sat_scores.average.overall,latest.student.size,");
    urlStringBuilder.append(
        "latest.cost.attendance.academic_year,latest.student.demographics.men,");
    urlStringBuilder.append("latest.student.demographics.women&api_key=");
    urlStringBuilder.append(univizConfig.scorecardApiKey());

    // Make REST call
    return new InputStreamReader(getStreamFromUrl(urlStringBuilder.toString()));
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
