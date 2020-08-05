package com.google.univiz.scorecard;

import java.util.List;
import java.util.Set;

public interface CollegeData {

  /** TODO(biancamacias): decide what getSuggestions does */
  List<ScorecardData> getSuggestions(String query);

  /**
   * Returns a list of ScorecardData to be used by the servlets.
   *
   * @param ids A list of college IDs that were selected by the user.
   * @param fieldNames A list of fieldNames of data requested that will be used to call the
   *     CollegeScorecard API?
   * @return A list of ScorecardData types to be used in the back-end.
   */
  List<ScorecardData> getCollegesById(Set<String> ids, Set<String> fieldNames);

  /**
   * Returns CollegeScorecard API call as JSON.
   *
   * @param ids A list of college IDs that were selected by the user.
   * @param fieldNames A list of fieldNames of data that will be used in the URL that calls the API.
   * @return A string that holds the JSON response.
   */
  String scorecardApiCall(List<String> ids, List<String> fieldNames);
}
