package com.google.univiz.api;

import java.util.ArrayList;
import java.util.List;

class SearchResourceImpl implements SearchResource {

  public List<SearchData> getSearchSuggestions(List<String> collegeNames) {
    List<SearchData> searchDataList = new ArrayList<>();
    List<SuggestionData> collegeSuggestions = getSuggestions(collegeNames);
    for (SuggestionData college : collegeSuggestions) {
      SearchData searchDataObj =
          SearchData.create(college.getCollegeName(), college.getCollegeId());
      searchDataList.add(searchDataObj);
    }
    return searchDataList;
  }
}
