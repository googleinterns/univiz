package com.google.univiz;

import java.util.List;
import java.util.Set;

/**
 * CollegeDataApi is an interface for the CollegeDataApiImpl class that interacts with external APIs
 * to send REST requests.
 */
public interface CollegeDataApi {

  List<CollegeData> getCollegesById(Set<CollegeId> ids);
}
