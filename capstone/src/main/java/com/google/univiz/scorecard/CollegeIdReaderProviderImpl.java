package com.google.univiz.scorecard;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

import com.google.gson.annotations.SerializedName;
import com.google.univiz.api.CollegeId;
import com.google.univiz.config.UnivizConfig;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.inject.Inject;

final class CollegeIdReaderProviderImpl implements CollegeIdReaderProvider {
  private final UnivizConfig univizConfig;
  private final Set<String> fields;

  @Inject
  protected CollegeIdReaderProviderImpl(UnivizConfig univizConfig) {
    this.univizConfig = univizConfig;
    fields =
        Arrays.stream(ScorecardData.class.getDeclaredFields())
            .map(field -> field.getAnnotation(SerializedName.class))
            .filter(Objects::nonNull)
            .map(SerializedName::value)
            .collect(toSet());
  }

  @Override
  public Reader getReaderFromCollegeIds(List<CollegeId> ids) throws IOException {
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
      return url.openStream();
    } catch (MalformedURLException e) {
      throw new AssertionError(e);
    }
  }
}
