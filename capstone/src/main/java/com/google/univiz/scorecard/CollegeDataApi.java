package com.google.univiz.scorecard;

import com.google.univiz.api.resource.CollegeId;
import java.util.List;

/** Provides access to college related data. */
public interface CollegeDataApi {

  List<CollegeData> getCollegesById(List<CollegeId> ids);
}
