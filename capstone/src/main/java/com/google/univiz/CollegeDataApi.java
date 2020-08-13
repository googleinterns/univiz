package com.google.univiz;

import com.google.univiz.api.CollegeId;
import java.util.List;

/** Provides access to college related data. */
public interface CollegeDataApi {

  List<CollegeData> getCollegesById(List<CollegeId> ids);
}
