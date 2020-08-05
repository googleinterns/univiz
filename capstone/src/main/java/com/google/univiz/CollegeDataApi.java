package com.google.univiz;

import java.util.List;
import java.util.Set;

public interface CollegeDataApi {
  /**
   * 
   */
  List<CollegeData> getCollegesById(Set<String> ids, Set<String> names);
}
