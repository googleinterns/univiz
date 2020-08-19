package com.google.univiz.scorecard;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;

import com.google.gson.annotations.SerializedName;
import com.google.univiz.api.CollegeId;
import com.google.univiz.config.UnivizConfig;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.inject.Inject;

final class URLProviderImpl implements URLProvider {
  private final UnivizConfig univizConfig;
  private final Set<String> fields;

  @Inject
  protected URLProviderImpl(UnivizConfig univizConfig) {
    this.univizConfig = univizConfig;
    fields =
        Arrays.stream(ScorecardData.class.getDeclaredMethods())
            .map(field -> field.getAnnotation(SerializedName.class))
            .filter(Objects::nonNull)
            .map(SerializedName::value)
            .collect(toCollection(LinkedHashSet::new));
  }

  @Override
  public String getUrlFromCollegeIds(List<CollegeId> ids) {
    String stringWithFields = fields.stream().collect(joining(","));
    StringBuilder urlStringBuilder = new StringBuilder();
    urlStringBuilder.append("https://api.data.gov/ed/collegescorecard/v1/schools.json?id=");
    String stringWithIds =
        ids.stream().map(CollegeId::id).map(String::valueOf).collect(joining(","));
    urlStringBuilder.append(stringWithIds);
    urlStringBuilder.append("&per_page=1&fields=");
    urlStringBuilder.append(stringWithFields);
    urlStringBuilder.append("&api_key=");
    urlStringBuilder.append(univizConfig.scorecardApiKey());
    return urlStringBuilder.toString();
  }
}
