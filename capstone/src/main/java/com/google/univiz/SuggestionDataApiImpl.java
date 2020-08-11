package com.google.univiz;

import java.util.List;
import org.json.simple.JSONObject;

public class SuggestionDataApiImpl implements SuggestionDataApi {
  private static final frontUrl = "https://api.data.gov/ed/collegescorecard/v1/schools.json?school.name=";
  private JSONObject requestRESTApiData(String fields, String proposedCollegeName);
  private List<SuggestionData> convertJsonToSuggestionData(JSONObject suggestionResults);
  @Override
  public List<SuggestionData> getSuggestions(String collegeName);
}
