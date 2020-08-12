package com.google.univiz.api;

//TODO: imports
//
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

class SearchResourceImpl implements SearchResource {
  private final SuggestionDataApi suggestionApi;
  
  @Inject
  SearchResourceImpl(SuggestionDataApi suggestionApi) {
    this.suggestionApi =  suggestionApi;
  }

  @Override
  public List<SearchData> getSearchSuggestions(String partialCollegeName) {
    List<SuggestionData> collegeSuggestions = suggestionApi.getSuggestions(partialCollegeName);
    return collegeSuggestions.stream()
	                     .map(college -> SearchData.create(college.collegeName(), college.collegeId()))
	                     .collect(Collectors.toList());
  }
}
