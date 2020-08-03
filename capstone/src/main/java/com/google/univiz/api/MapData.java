package com.google.univiz.api;

/*
 * The MapData interface is responsible for all data management/processing specific for the maps
 * feature of the UniViz webapp.
 */
public interface MapData {
  /*
   * Takes a list of college names and returns a CollegeMapsData object to the caller. This object
   * contains all data relevant to the Maps feature.
   *
   * @args Takes a list of CollegeIDs, which is a class with two components: a college name and a 
   * unique college id
   *
   * @return Returns a list of CollegeMapsData objects which contain all information necessary for
   * a geographic visualization 
   */
  List<CollegeMapsData> getMapData(List<CollegeId> colleges);
}
