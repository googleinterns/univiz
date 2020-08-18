package com.google.univiz.scorecard;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

import com.google.gson.annotations.SerializedName;
import com.google.univiz.api.CollegeId;
import com.google.univiz.config.UnivizConfig;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.inject.Inject;

class URLProviderImpl implements URLProvider {
  private final UnivizConfig univizConfig;
  private final Set<String> fields;

  @Inject
  protected URLProviderImpl(UnivizConfig univizConfig) {
    this.univizConfig = univizConfig;
    fields =
        Arrays.stream(ScorecardData.class.getDeclaredFields())
            .map(field -> field.getAnnotation(SerializedName.class))
            .filter(Objects::nonNull)
            .map(SerializedName::value)
            .collect(toSet());
  }

  @Override
  public String getUrlFromCollegeIds(List<CollegeId> ids) {
    String stringWithFields = fields.stream().collect(joining(","));
    final StringBuilder urlStringBuilder = new StringBuilder();
    urlStringBuilder.append("https://api.data.gov/ed/collegescorecard/v1/schools.json?id=");
    String stringWithIds = ids.stream().map(id -> Integer.toString(id.id())).collect(joining(","));
    urlStringBuilder.append(stringWithIds);
    urlStringBuilder.append("&per_page=1&fields=");
    urlStringBuilder.append(stringWithFields);
    urlStringBuilder.append("&api_key=");
    urlStringBuilder.append(univizConfig.scorecardApiKey());
    return urlStringBuilder.toString();
  }
}
