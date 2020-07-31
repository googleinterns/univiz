package com.google.univiz.api;

import java.util.List;

public interface SearchData {
  /**
   *
   * Method: getCollegeSuggestions
   *
   * Description:
   * This function take a potential college name
   * fragment (provided by the user) and uses the
   * API in use to determine whether this string 
   * fragment is present in any college names within 
   * the API database 
   * If so, then a list of college name containing 
   * the provided string fragment
   */
  List<String> getCollegeSuggestions(String currentCollegeNameFragment);
}
