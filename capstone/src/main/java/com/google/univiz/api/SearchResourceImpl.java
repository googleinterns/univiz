package com.google.univiz.api;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

class SearchResourceImpl implements SearchResource {
  private List<SuggestionData> collegeSuggestions;

  @Inject
  public void Restaurant(collegeSuggestions) {
    this.collegeSuggestions = collegeSuggestions;
  }

  public List<SearchData> getSearchSuggestions(String partialCollegeName) {
    List<SearchData> searchDataList = new ArrayList<>();
    for (SuggestionData college : this.collegeSuggestions) {
      SearchData searchDataObj =
          SearchData.create(college.getCollegeName(), college.getCollegeId());
      searchDataList.add(searchDataObj);
    }
    return searchDataList;
  }
}
