package com.google.univiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public final class CollegeScoreCardLibrary {
  private final String apiBeginningURL = "https://api.data.gov/ed/collegescorecard/v1/schools.json?";
  
  /*Helper Function*/
  private JSONObject getCollegeNameJson(String collegeName) {
    String schoolNameQuery = "school.name=" + collegeName + "&";
    String fieldQuery = "field=school.name&";
    String apiKeyForURL = "api_key=" + apiKey; /*TODO: Update apiKey variable*/
    String apiURL = apiBeginningURL + 
	    	    schoolNameQuery + 
		    fieldQuery +
		    apiKeyForURL;
    InputStream readURLData = new URL(apiURL).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(readURLData, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsontext);
      return json;
    } catch (Exception e) {
      System.out.println("Exception caught!");
    } finally {
      readURLData.close(); /*Close InputStream*/
    }  
  }

  /*General Use Functions*/
  public boolean verifyCollegeNameIsValid(String collegeName) {
    /* ASSUMPTIONS:
     * -Each college has a UNIQUE name
     * -Any query which returns multiple college
     *  names will be marked as invalid
     */
    JSONObject collegeNameJson = getCollegeNameJson(collegeName); 
    return (collegeNameJson.metadata.total == 1);
  }
  
  /*Search Feature Functions*/
  public List<String> findAutocompleteSuggestions(String currentName) {
    /*TODO: Autocomplete suggestions*/
  }
  
  /*Maps Feature Functions*/
  public void getMapDataForColleges(List<String> collegeNames) {
    /*TODO: Data for Maps*/
  }

  /*DataVis Feature Functions*/
  public void getDataVisDataForColleges(List<String> collegeNames) {
    /*TODO: Data for DataVis*/
  }
  
}
