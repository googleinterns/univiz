package com.google.univiz;

import java.util.List;
import java.util.Set;

/**
 * The CollegeDataApi
 */
public interface CollegeDataApi {

  List<CollegeData> getCollegesById(Set<String> ids);
}
