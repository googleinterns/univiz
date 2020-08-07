package com.google.univiz;

import java.util.List;
import java.util.Set;

/**
 * The CollegeDataApi interface is responsible for all REST requests and JSON parsing to the
 * CollegeData class.
 */
public interface CollegeDataApi {

  List<CollegeData> getCollegesById(Set<CollegeId> ids);
}
