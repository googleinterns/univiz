package com.google.univiz.api;

/** SearchData represents the college data specific to the Search feature */
public class SearchData {
  String collegeName;
  int collegeId;

  public SearchData(String collegeName, int collegeId) {
    this.collegeName = collegeName;
    this.collegeId = collegeId;
  }
}
