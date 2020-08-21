package com.google.univiz.api.resource;

import com.google.univiz.api.MapsResource;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.MapsData;
import java.util.List;

final class MapsResourceImpl implements MapsResource {

  @Override
  public List<MapsData> getMapsData(List<CollegeId> colleges) {
    throw new UnsupportedOperationException();
  }
}
