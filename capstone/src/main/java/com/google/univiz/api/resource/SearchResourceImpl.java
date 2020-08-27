package com.google.univiz.api.resource;

import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.SearchData;
import com.google.univiz.api.representation.SuggestionResponse;
import com.google.univiz.scorecard.SuggestionDataApi;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

final class SearchResourceImpl implements SearchResource {
  private static final int MAX_SUGGESTIONS = 10;
  private final SuggestionDataApi suggestionApi;

  @Inject
  SearchResourceImpl(SuggestionDataApi suggestionApi) {
    this.suggestionApi = suggestionApi;
  }

  @Override
  public List<SearchData> getSearchSuggestions(String partialCollegeName) throws IOException {
    SuggestionResponse collegeSuggestions = suggestionApi.getCollegeSuggestions(partialCollegeName);
    List<SearchData> suggestionCandidates =
        collegeSuggestions.suggestions().stream()
            .filter(suggestion -> suggestion.name() != null)
            .map(college -> SearchData.create(college.name(), CollegeId.create(college.id())))
            .collect(Collectors.toList());
    Iterator<SearchData> it = suggestionCandidates.iterator();
    String partialCollegeNameUpper = partialCollegeName.toUpperCase();
    while (it.hasNext()) {
      SearchData searchData = it.next();
      String name = searchData.collegeName().toUpperCase();
      if (partialCollegeNameUpper.compareTo(name.substring(0, partialCollegeName.length())) != 0) {
        it.remove();
      }
    }

    return suggestionCandidates.stream().limit(MAX_SUGGESTIONS).collect(Collectors.toList());
  }
}
