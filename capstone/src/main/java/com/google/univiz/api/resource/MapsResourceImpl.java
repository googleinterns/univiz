package com.google.univiz.api.resource;

import com.google.univiz.api.representation.CollegeData;
import com.google.univiz.api.CollegeDataApi;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.MapsData;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MapsResourceImpl implements MapsResource {
  private final CollegeDataApi collegeDataApi;
  private final CollegeDataConverter converter;

  @Inject
  MapsResourceImpl(CollegeDataApi collegeDataApi, CollegeDataConverter converter) {
    this.collegeDataApi = collegeDataApi;
    this.converter = converter;
  }

  @Override
  public List<MapsData> getMapsData(List<CollegeId> ids) {
    List<CollegeData> colleges = collegeDataApi.getCollegesById(ids);
    List<MapsData> mapsData = new ArrayList<>();

    for (CollegeData college : colleges) {
      MapsData mapsDataFromCollege = converter.convert(college);
      mapsData.add(mapsDataFromCollege);
    }

    return mapsData;
  }
}
