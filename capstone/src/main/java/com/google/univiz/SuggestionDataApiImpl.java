package com.google.univiz;

import java.util.List;
import org.json.simple.JSONObject;

public class SuggestionDataApiImpl implements SuggestionDataApi {
  private static final frontUrl = "https://api.data.gov/ed/collegescorecard/v1/schools.json?school.name=";
  
  /** Makes REST request to the CollegeScorecard API  */
  private JSONObject requestRESTApiData(String fields, String proposedCollegeName) {
  }

  /** Takes REST API Json response and converts it to SuggestionData */
  private List<SuggestionData> convertJsonToSuggestionData(JSONObject suggestionResults) {
  
  }
  
  /** TODO: Javadoc */
  @Override
  public List<SuggestionData> getSuggestions(String collegeName);
}
