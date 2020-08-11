package com.google.univiz.api;

import java.util.ArrayList;
import java.util.List;

/** Implementation of SearchResource Interface */
public class SearchResourceImpl implements SearchResource {

  public List<SearchData> getSearchSuggestions(String partialCollegeName) {
    List<SearchData> searchDataList = new ArrayList<>();
    List<SuggestionData> collegeSuggestions = getSuggestions(partialCollegeName);
    for (SuggestionData college : collegeSuggestions) {
      SearchData searchDataObj =
          SearchData.create(college.getCollegeName(), college.getCollegeId());
      searchDataList.add(searchDataObj);
    }
    return searchDataList;
  }
}
