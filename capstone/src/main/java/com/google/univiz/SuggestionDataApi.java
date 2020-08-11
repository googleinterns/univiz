package com.google.univiz;

import java.util.List;

/**
 * Communicates directly with the data source to get a list of college suggestions related to a client
 * query
 */
public interface SuggestionDataApi {
  /**
   * Takes a college name and returns a list of SuggestionData objects, where each object
   * is unique to each college name provided in the first list
   */
  List<SuggestionData> getCollegeSuggestions(String collegeName);
}
