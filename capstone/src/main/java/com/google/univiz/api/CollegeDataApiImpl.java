package com.google.univiz.api;

import com.google.univiz.api.representation.CollegeData;
import com.google.univiz.api.representation.CollegeId;
import java.util.ArrayList;
import java.util.List;

final class CollegeDataApiImpl implements CollegeDataApi {

  public List<CollegeData> getCollegesById(List<CollegeId> ids) {
    List<CollegeData> colleges = new ArrayList<>();
    return colleges;
  }
}