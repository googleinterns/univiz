package com.google.univiz.collegescorecarddata;

import java.util.List;

public interface CollegeScoreCard {
  /** DataVis Feature Functions */
  List<UniversityData> getDataVisData(List<UniversityData> collegeDatum);
}
