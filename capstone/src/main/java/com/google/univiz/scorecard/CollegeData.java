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
   * getCollegesById takes ids and field names of colleges selected and return a list of
   * ScorecardData to be used by the servlets.
   */
  public List<ScorecardData> getCollegesById(List<String> ids, List<String> fieldNames) {}

  /**
   * scorecardApiCall takes ids and field names of colleges selected to call the CollegeScorecardAPI
   * and return the data as JSON.
   */
  public String scorecardApiCall(List<String> ids, List<String> fieldNames) {}
}
