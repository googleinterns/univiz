package com.google.univiz.api;

import java.util.ArrayList;
import java.util.List;

/** Implementation of SearchResource Interface */
public class SearchResourceImpl extends SearchResource {

  public List<SearchData> getSearchSuggestions(List<SuggestionData> collegeNames) {
    List<SearchData> searchDataList = new ArrayList<SearchData>();
    for (SuggestionData college : collegeNames) {
      SearchData searchDataObj = new SearchData(college.getName(), college.getCollegeId());
      searchDataList.add(searchDataObj);
    }
    return searchDataList;
  }
}
