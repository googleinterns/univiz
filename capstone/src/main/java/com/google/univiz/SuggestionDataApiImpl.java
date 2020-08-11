package com.google.univiz;

import java.util.List;
import org.json.simple.JSONObject;

public class SuggestionDataApiImpl implements SuggestionDataApi {
  private static final frontUrl = "https://api.data.gov/ed/collegescorecard/v1/schools.json?school.name=";
  
  /** TODO: Javadoc */
  private JSONObject requestRESTApiData(String fields, String proposedCollegeName);
  /** TODO: Javadoc */
  private List<SuggestionData> convertJsonToSuggestionData(JSONObject suggestionResults) {
  
  }
  
  /** TODO: Javadoc */
  @Override
  public List<SuggestionData> getSuggestions(String collegeName);
}
