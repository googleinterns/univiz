package com.google.univiz.collegescorecarddata;

import java.util.List;

public interface MapData {
  /** Maps Feature Functions */
  List<UniversityData> getMapData(List<UniversityData> collegeDatum);
}
