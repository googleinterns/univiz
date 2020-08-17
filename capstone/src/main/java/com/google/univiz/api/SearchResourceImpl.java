package com.google.univiz.api;

import java.util.ArrayList;
import java.util.List;

class SearchResourceImpl implements SearchResource {

  public List<SearchData> getSearchSuggestions(List<SuggestionData> collegeNames) {
    List<SearchData> searchDataList = new ArrayList<>();
    for (SuggestionData college : collegeNames) {
      SearchData searchDataObj =
          SearchData.create(college.getCollegeName(), college.getCollegeId());
      searchDataList.add(searchDataObj);
    }
    return searchDataList;
  }
}