package com.google.univiz.api;

import com.google.univiz.CollegeId;
import java.util.List;

/**
 * The MapsResource interface is responsible for all data management/processing specific for the maps
 * feature of the UniViz webapp.
 */
public interface MapsResource {
  /**
   * Takes a list of college ids and returns a list of MapsData objects to the caller. This object
   * contains all data relevant to the Maps feature.
   */
  List<MapsData> getMapData(List<CollegeId> colleges);
}
