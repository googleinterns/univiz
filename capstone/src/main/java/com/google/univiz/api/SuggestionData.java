package com.google.univiz.api;

/** THIS IS A DUMMY CLASS TO ALLOW COMPILATION: REMOVE BEFORE MERGING */
public class SuggestionData {
  String collegeName;
  int collegeId;

  public SuggestionData(String collegeName, int collegeId) {
    this.collegeName = collegeName;
    this.collegeId = collegeId;
  }
  
  public String getCollegeName() {
    return collegeName;
  }
  
  public int getCollegeId() {
    return collegeId;
  }
}
