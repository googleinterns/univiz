package com.google.univiz.api;

//TODO: imports
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class SearchResourceImpl implements SearchResource {
  private final SuggestionApi suggestionApi;
  
  @Inject
  SearchResourceImpl(SuggestionApi suggestionApi) {
    this.suggestionApi =  suggestionApi;
  }

  @Override
  public static List<SearchData> getSearchSuggestions(String partialCollegeName) {
    List<SuggestionData> collegeSuggestions = suggestionApi.getSuggestions(partialCollegeName);
    return collegeSuggestions.stream()
	                     .map(college -> SearchData.create(college.getCollegeName(), college.getCollegeId()))
	                     .collect(Collectors.toList());
  }
}
