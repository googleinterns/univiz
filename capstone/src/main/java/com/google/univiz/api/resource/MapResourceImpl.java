package com.google.univiz.api.resource;

import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.MapsData;
import java.util.ArrayList;
import java.util.List;

public class MapResourceImpl implements MapResource {

  public List<MapsData> getMapData(List<CollegeId> ids) {
    List<MapsData> mapsData = new ArrayList<>();
    return mapsData;
  }
}