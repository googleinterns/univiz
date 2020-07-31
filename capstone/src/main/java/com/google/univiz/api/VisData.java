package com.google.univiz.api;

import java.util.List;

/**
 * 
 * VisData.java
 *
 * Description:
 * This interface is responsible for all
 * data management/processing specific for
 * the visualization feature of the UniViz 
 * webapp.
 */
public interface VisData {
  /** 
   * Method: getVisData
   *
   * Description:
   * Takes a list of UniversityData objects
   * and populates all fields relevant to the 
   * visualization feature in each object
   * This function returns that same list with
   * the updated data fields
   *
   */
  List<UniversityData> getVisData(List<UniversityData> collegeDatum);
}
