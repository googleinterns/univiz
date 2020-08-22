package com.google.univiz.api.resource;

import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.MapsData;
import java.io.IOException;
import java.util.List;

/**
 * The MapsResource interface is responsible for all data management/processing specific for the
 * maps feature of the UniViz webapp.
 */
public interface MapsResource {
  /**
   * Takes a list of college ids and returns a list of MapsData objects to the caller. This object
   * contains all data relevant to the Maps feature.
   */
  List<MapsData> getMapsData(List<CollegeId> colleges) throws IOException;
}
