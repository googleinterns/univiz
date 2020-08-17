package com.google.univiz.api.resource;

import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.MapsData;
import java.util.List;

/**
 * The MapResource interface is responsible for all data management/processing specific for the maps
 * feature of the UniViz webapp.
 */
public interface MapResource {
  /**
   * Takes a list of college ids and returns a list of MapsData objects to the caller. This object
   * contains all data relevant to the Maps feature.
   */
  List<MapsData> getMapData(List<CollegeId> colleges);
}
