package com.google.univiz.api.resource;

import com.google.common.collect.Lists;
import com.google.univiz.api.SuggestionDataApi;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.SearchData;
import com.google.univiz.api.representation.SuggestionResponse;
import com.google.univiz.api.resource.SearchResource;
import java.util.List;
import java.util.ArrayList;
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
    if (partialCollegeName == null) {
      List<SearchData> emptyList = new ArrayList<>();
      return emptyList;
    }
    SuggestionResponse collegeSuggestions = suggestionApi.getCollegeSuggestions(partialCollegeName);
    return collegeSuggestions.suggestions().stream()
        .map(college -> SearchData.create(college.name(), CollegeId.create(college.id())))
        .collect(Collectors.toList());
  }
}
