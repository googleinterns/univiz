package com.google.univiz.api;

import com.google.univiz.api.representation.CollegeData;
import com.google.univiz.api.representation.CollegeId;
import java.io.IOException;
import java.util.List;

final class CollegeDataApiImpl implements CollegeDataApi {

  @Override
  public List<CollegeData> getCollegesById(List<CollegeId> ids) throws IOException {
    throw new UnsupportedOperationException();
  }
}
