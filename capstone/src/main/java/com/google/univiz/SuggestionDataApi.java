package com.google.univiz;

import java.util.List;

/**
 * Communicates directly with the REST API to get a list of college suggestions related to a client
 * query
 */
public interface SuggestionDataApi {
  /**
   * Takes a list of college names and returns a list of SuggestionData objects, where each object
   * is unique to each college name providede in the first list
   */
  List<SuggestionData> getCollegeSuggestions(String collegeNames);
}
