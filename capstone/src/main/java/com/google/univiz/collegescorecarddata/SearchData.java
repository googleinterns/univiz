package com.google.univiz.collegescorecarddata;

import java.util.List;

public interface CollegeScoreCard {
  /** Search Feature Functions */
  List<UniversityData> getCollegeSuggestions(String currName);
}
