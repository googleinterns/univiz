package com.google.univiz.api;

import java.util.ArrayList;
import java.util.List;

/** Implementation of SearchResource Interface */
public class SearchResourceImpl implements SearchResource {

  @Override
  public List<SearchData> getSearchSuggestions(String collegeNameQuery) {
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
