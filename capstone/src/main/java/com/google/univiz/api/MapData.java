package com.google.univiz.api;

import java.util.List;

/**
 * This interface is responsible for all data management/processing specific for the maps feature of
 * the UniViz webapp.
 */
public interface MapData {
  /**
   * Takes a list of UniversityData objects and populates all fields relevant to the maps feature in
   * each object; This function returns that same list withthe updated data fields
   */
  List<UniversityData> getMapData(List<UniversityData> collegeDatum);
}
