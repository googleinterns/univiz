package com.google.univiz.api;

import com.google.univiz.CollegeData;
import com.google.univiz.CollegeDataApi;
import com.google.univiz.CollegeId;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MapsResourceImpl implements MapsResource {
  private final CollegeDataApi collegeDataApi;

  @Inject
  MapsResourceImpl(CollegeDataApi collegeDataApi) {
    this.collegeDataApi = collegeDataApi;
  }

  @Override
  public List<MapsData> getMapData(List<CollegeId> ids) {
    List<CollegeData> colleges = collegeDataApi.getCollegesById(ids);
    List<MapsData> mapsData = new ArrayList<>();
    CollegeDataConverter converter = new CollegeDataConverter();

    if (colleges.size() == 0) {
      return mapsData;
    } else {
      for (CollegeData college : colleges) {
        MapsData mapsDataFromCollege = converter.convert(college);
        mapsData.add(mapsDataFromCollege);
      }
    }

    return mapsData;
  }
}
