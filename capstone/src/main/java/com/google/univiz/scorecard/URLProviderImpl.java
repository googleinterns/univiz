package com.google.univiz.scorecard;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;

import com.google.gson.annotations.SerializedName;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.config.UnivizConfig;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.inject.Inject;

final class URLProviderImpl implements URLProvider {
  private static final String FRONT_URL = "https://api.data.gov/ed/collegescorecard/v1/schools.json?";
  private static final String QUERY_TYPE_NAME = "school.name=";
  private static final String QUERY_TYPE_ID = "id=";
  private static final String PER_PAGE = "&per_page=";
  private static final String FIELDS_PARAM = "&fields=";
  private static final String SUGGESTION_FIELDS = "id,school.name";
  private final UnivizConfig univizConfig;
  private final Set<String> fields;

  @Inject
  URLProviderImpl(UnivizConfig univizConfig) {
    this.univizConfig = univizConfig;
    fields =
        Arrays.stream(ScorecardData.class.getDeclaredMethods())
            .map(field -> field.getAnnotation(SerializedName.class))
            .filter(Objects::nonNull)
            .map(SerializedName::value)
            .sorted()
            .collect(toCollection(LinkedHashSet::new));
  }

  @Override
  public String getUrl(String partialCollegeName, List<CollegeId>... ids) throws RuntimeException {
    StringBuilder urlStringBuilder = new StringBuilder();
    urlStringBuilder.append(FRONT_URL);
    if (partialCollegeName != null && (ids.length == 0)) {
      urlStringBuilder = buildSuggestionUrl(urlStringBuilder, partialCollegeName);
    } else if (ids.length > 0) {
      urlStringBuilder = buildDataUrl(urlStringBuilder, ids[0]);
    } else {
      throw new RuntimeException("No valid URL intent was provided!");
    }
    urlStringBuilder.append("&api_key=");
    urlStringBuilder.append(univizConfig.scorecardApiKey());
    return urlStringBuilder.toString();
  }

  private StringBuilder buildSuggestionUrl(
      StringBuilder urlStringBuilder, String partialCollegeName) {
    urlStringBuilder.append(QUERY_TYPE_NAME);
    urlStringBuilder.append(partialCollegeName);
    urlStringBuilder.append(FIELDS_PARAM);
    urlStringBuilder.append(SUGGESTION_FIELDS);
    return urlStringBuilder;
  }

  private StringBuilder buildDataUrl(StringBuilder urlStringBuilder, List<CollegeId> ids) {
    String stringWithFields = fields.stream().collect(joining(","));
    String stringWithIds =
        ids.stream().map(CollegeId::id).map(String::valueOf).collect(joining(","));
    urlStringBuilder.append(QUERY_TYPE_ID);
    urlStringBuilder.append(stringWithIds);
    urlStringBuilder.append(PER_PAGE);
    urlStringBuilder.append(String.format("%d", ids.size()));
    urlStringBuilder.append(FIELDS_PARAM);
    urlStringBuilder.append(stringWithFields);
    return urlStringBuilder;
  }
}
