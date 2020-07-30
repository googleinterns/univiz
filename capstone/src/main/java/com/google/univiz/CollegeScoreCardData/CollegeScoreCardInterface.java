interface CollegeScoreCardInterface {
  /*General Use Functions*/
  public boolean validateCollegeName(String collegeName);
  public List<UniversityData> getCollegesData(List<String> collegeNames);

  /*Search Feature Functions*/
  public List<UniversityData> getCollegeSuggestions(String currName);

  /*Maps Feature Functions*/
  public List<UniversityData> updateMapData(List<UniversityData> collegeDatum);
  
  /*DataVis Feature Functions*/
  public List<UniversityData> updateDataVisData(List<UniversityData> collegeDatum);
}
