package com.google.univiz.api;

/*
 * The MapData interface is responsible for all data management/processing specific for the maps
 * feature of the UniViz webapp.
 */
public interface MapData {
  /*
   * Takes a list of college names and returns a CollegeMapsData object to the caller. This object
   * contains all data relevant to the Maps feature.
   */
  CollegeMapsData getMapData(List<String> collegeNames);
}
