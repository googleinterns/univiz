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
import java.util.Set;
import java.util.HashSet;
import javax.inject.Inject;

class CollegeIdReaderProviderImpl implements CollegeIdReaderProvider {
  private final UnivizConfig univizConfig;

  @Inject
  protected CollegeIdReaderProviderImpl(UnivizConfig univizConfig) {
    this.univizConfig = univizConfig;
  }

  @Override
  public Reader getReaderFromCollegeIds(List<CollegeId> ids) throws IOException {
    Set<String> fields = new HashSet<>();
    fields.add("id");
    fields.add("school.name");
    fields.add("school.city");
    fields.add("school.main_campus");
    fields.add("location.lat");
    fields.add("location.lon");
    fields.add("school.carnegie_size_setting");
    fields.add("latest.admissions.admission_rate_overall");
    fields.add("latest.admissions.sat_scores.average.overall");
    fields.add("latest.student.size");
    fields.add("latest.cost.attendance.academic_year");
    fields.add("latest.student.demographics.men");
    fields.add("latest.student.demographics.women");
    String stringWithFields = fields.stream().collect(joining(","));
    final StringBuilder urlStringBuilder = new StringBuilder();
    urlStringBuilder.append("https://api.data.gov/ed/collegescorecard/v1/schools.json?id=");
    String stringWithIds = ids.stream().map(id -> Integer.toString(id.id())).collect(joining(","));
    urlStringBuilder.append(stringWithIds);
    urlStringBuilder.append("&per_page=1&fields=");
    urlStringBuilder.append(stringWithFields);
    urlStringBuilder.append("&api_key=");
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
