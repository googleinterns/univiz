package com.google.univiz.api;

import java.util.List;

/**
 * The SearchResoure interface is responsible for all data management/processing specific for the
 * search feature of the UniViz webapp.
 */
public interface SearchResource {
  /**
   * Given a potentially incomplete search query, returns a list of colleges that would match the
   * query.
   *
   * @param query The search query as the user provided. It should be a non-empty and a non-null
   *     string. 
   * @return A list of possible matching colleges. The list is ordered such that the best
   *     match is at the beginning of the list. The list may be empty which indicates there are no
   *     possible matches. The list is also not an exhaustive set of matches. It only includes few
   *     top results. If the string is invalid (empty, null, etc.), an empty list will be returned
   */
  List<SearchData> getSearchSuggestions(String collegeNames);
}
