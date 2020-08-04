package com.google.univiz.scorecard;

/**
 * CollegeData is an interface that contains methods and functions which communicate with the
 * CollegeScorecardAPI to obtain college data from the URL in JSON format and parse them to become
 * ScorecardData java objects.
 */
public interface CollegeData {

  /** TODO(biancamacias): decide what getSuggestions does */
  public List<ScorecardData> getSuggestions(String suggestion) {}

  /**
   * Returns a list of ScorecardData to be used by the servlets.
   *
   * @param ids A list of college IDs that were selected by the user.
   * @param fieldNames A list of fieldNames of data requested that will be used to call the
   *     CollegeScorecard API?
   * @return A list of ScorecardData types to be used in the back-end.
   */
  public List<ScorecardData> getCollegesById(List<String> ids, List<String> fieldNames) {}

  /**
   * Returns CollegeScorecard API call as JSON.
   *
   * @param ids A list of college IDs that were selected by the user.
   * @param fieldNames A list of fieldNames of data that will be used in the URL that calls the API.
   * @return A string that holds the JSON response.
   */
  public String scorecardApiCall(List<String> ids, List<String> fieldNames) {}
}
