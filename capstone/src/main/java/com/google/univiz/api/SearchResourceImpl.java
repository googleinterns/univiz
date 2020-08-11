package com.google.univiz.api;

import java.util.ArrayList;
import java.util.List;

class SearchResourceImpl implements SearchResource {

  @Override
  public static List<SearchData> getSearchSuggestions(String partialCollegeName) {
    List<SearchData> searchDataList = new ArrayList<>();
    List<SuggestionData> collegeSuggestions = getSuggestions(partialCollegeName); // TODO
    for (SuggestionData college : collegeSuggestions) {
      SearchData searchDataObj =
          SearchData.create(college.getCollegeName(), college.getCollegeId());
      searchDataList.add(searchDataObj);
    }
    return searchDataList;
  }
}
