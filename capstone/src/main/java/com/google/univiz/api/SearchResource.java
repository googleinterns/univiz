package com.google.univiz.api;

import java.util.List;

/**
 * The SearchResoure interface is responsible for all data management/processing specific for the
 * search feature of the UniViz webapp.
 */
public interface SearchResource {
  /**
   * Take a list of suggestion data and returns a list of Suggestions objects to the caller. This
   * object contains all data relevant to the Search feature.
   */
  List<SearchData> getSearchSuggestions(List<SuggestionData> collegeNames);
}
