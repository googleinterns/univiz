package com.google.univiz.api;

import java.util.List;

/**
 * This interface contains functions which will directly communicate with the API we use to obtain 
 * college data
 */
public interface GeneralCollegeData {
  /** 
   *  Checks the provided collegeName to see if the API in use has data on the college in question; 
   *  If so, returns true and if not, return false
   */
  boolean validateCollegeName(String collegeName);

  /**
   * Takes a list of collegeNames and returns a list of UniversityData objects which contains the as 
   * much of the desired data about the college as the API source can provide.  
   */
  List<UniversityData> getCollegesData(List<String> collegeNames);
}
