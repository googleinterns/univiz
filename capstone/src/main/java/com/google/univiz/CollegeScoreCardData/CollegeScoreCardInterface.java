package com.google.univiz.collegescorecarddata;

import java.util.List;

interface CollegeScoreCardInterface {
  /*General Use Functions*/
  boolean validateCollegeName(String collegeName);

  List<UniversityData> getCollegesData(List<String> collegeNames);

  /*Search Feature Functions*/
  List<UniversityData> getCollegeSuggestions(String currName);

  /*Maps Feature Functions*/
  List<UniversityData> getMapData(List<UniversityData> collegeDatum);

  /*DataVis Feature Functions*/
  List<UniversityData> getDataVisData(List<UniversityData> collegeDatum);
}
