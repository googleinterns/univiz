package com.google.univiz.api;

import java.util.List;

/**
 * The MapResource interface is responsible for all data management/processing specific for the maps
 * feature of the UniViz webapp.
 */
public interface MapResource {
  /**
   * Takes a list of college names and returns a list of MapsData objects to the caller. This object
   * contains all data relevant to the Maps feature.
   */
  List<MapsData> getMapData(List<CollegeId> colleges);
}
