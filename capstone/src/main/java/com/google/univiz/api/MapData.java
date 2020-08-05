package com.google.univiz.api;

/**
 * The MapData interface is responsible for all data management/processing specific for the maps
 * feature of the UniViz webapp.
 */
public interface MapData {
  /**
   * Takes a list of college names and returns a list of CollegeMapsData objects to the caller. This 
   * object contains all data relevant to the Maps feature.
   *
   * @param Takes a list of CollegeIDs which represents a list of unique colleges
   *
   * @return Returns a list of CollegeMapsData objects which contain all information necessary for
   * a geographic visualization 
   */
  List<CollegeMapsData> getMapData(List<CollegeId> colleges);
}
