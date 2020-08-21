package com.google.univiz.api;

import com.google.univiz.api.representation.CollegeData;
import com.google.univiz.api.representation.CollegeId;
import java.io.IOException;
import java.util.List;

/** Provides access to college related data. */
public interface CollegeDataApi {

  List<CollegeData> getCollegesById(List<CollegeId> ids) throws IOException;
}
