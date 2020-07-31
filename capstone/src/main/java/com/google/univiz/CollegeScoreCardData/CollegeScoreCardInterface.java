import java.util.List;

interface CollegeScoreCardInterface {
  /*General Use Functions*/
  public boolean validateCollegeName(String collegeName);

  public List<UniversityData> getCollegesData(List<String> collegeNames);

  /*Search Feature Functions*/
  public List<UniversityData> getCollegeSuggestions(String currName);

  /*Maps Feature Functions*/
  public List<UniversityData> getMapData(List<UniversityData> collegeDatum);

  /*DataVis Feature Functions*/
  public List<UniversityData> getDataVisData(List<UniversityData> collegeDatum);
}
