package com.google.univiz;

import java.util.List;
import java.util.Set;

/**
 * The CollegeDataApi interface is responsible for all REST requests and JSON parsing to the
 * CollegeData class. CollegeDataApi is the only class that will communicate with an external API.
 */
public interface CollegeDataApi {

  List<CollegeData> getCollegesById(Set<String> ids);
}
