package com.google.univiz.api;

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
  public List<SearchData> getSearchSuggestions(String partialCollegeName) {
    if (!partialCollegeName || !partialCollegeName.match("[a-zA-Z]+")) {
      partialCollegeName = "";
    }
    SuggestionResponse collegeSuggestions = suggestionApi.getCollegeSuggestions(partialCollegeName);
    return collegeSuggestions.suggestions().stream()
        .map(college -> SearchData.create(college.collegeName(), college.collegeId()))
        .collect(Collectors.toList());
  }
}
