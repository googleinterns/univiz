package com.google.univiz;

import java.util.Arrays;
import java.util.List;

public final class CollegeScoreCardLibrary {
  private final String apiURL = "https://api.data.gov/ed/collegescorecard/v1/schools.json?";
  
  /*General Use Functions*/
  public boolean verifyCollegeNameIsValid(String collegeName) {
    /* ASSUMPTIONS:
     * -Each college has a UNIQUE name
     * -Any query which returns multiple college
     *  names will be marked as invalid
     */
   
  }
  
  /*Search Feature Functions*/
  public List<String> findAutocompleteSuggestions(String currentName);
  
  /*Maps Feature Functions*/
  public void getMapDataForColleges(List<String> collegeNames);

  /*DataVis Feature Functions*/
  public void getDataVisDataForColleges(List<String> collegeNames);
  
}
