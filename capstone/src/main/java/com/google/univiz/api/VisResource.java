package com.google.univiz.api;

/**
 * The VisResource interface is responsible for all data management/processing specific for the vis
 * feature of the UniViz webapp.
 */
public interface VisResource {
  /**
   * Take a list of suggestion data and returns a list of VisData object to the caller. This object
   * contains all data relevant to the Vis feature.
   */
  List<VisData> getVisData(List<CollegeData> collegeNames);
}
