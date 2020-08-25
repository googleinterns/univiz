package com.google.univiz.api.resource;

import com.google.univiz.scorecard.SuggestionDataApi;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.SearchData;
import com.google.univiz.api.representation.SuggestionResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

final class SearchResourceImpl implements SearchResource {
  private final SuggestionDataApi suggestionApi;

  @Inject
  SearchResourceImpl(SuggestionDataApi suggestionApi) {
    this.suggestionApi = suggestionApi;
  }

  @Override
  public List<SearchData> getSearchSuggestions(String partialCollegeName) throws IOException {
    SuggestionResponse collegeSuggestions = suggestionApi.getCollegeSuggestions(partialCollegeName);
    return collegeSuggestions.suggestions().stream()
        .filter(suggestion -> suggestion.name() != null)
        .map(college -> SearchData.create(college.name(), CollegeId.create(college.id())))
        .collect(Collectors.toList());
  }
}
