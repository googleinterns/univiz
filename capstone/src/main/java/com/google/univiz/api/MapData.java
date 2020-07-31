package com.google.univiz.api;

import java.util.List;

/** 
 *
 * MapData.java
 *
 * Description:
 * This interface is responsible for all
 * data management/processing specific for
 * the maps feature of the UniViz webapp.
 *
 */
public interface MapData {
  /** 
   * Method: getMapData
   *
   * Description:
   * Takes a list of UniversityData objects
   * and populates all fields relevant to the 
   * maps feature in each object
   * This function returns that same list with
   * the updated data fields
   *
   * */
  List<UniversityData> getMapData(List<UniversityData> collegeDatum);
}
