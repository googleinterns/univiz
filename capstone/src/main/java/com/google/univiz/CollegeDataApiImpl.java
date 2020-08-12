package com.google.univiz;

import java.util.ArrayList;
import java.util.List;

public class CollegeDataApiImpl implements CollegeDataApi {
  @Override
  public List<CollegeData> getCollegesById(List<CollegeId> ids) {
    List<CollegeData> colleges = new ArrayList<>();
    for (CollegeId id: ids) {
      // Make REST calls?
    }
    return colleges;
  }
}
