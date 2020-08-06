package com.google.univiz.api;

/** SearchData represents the college data specific to the Search feature */
@AutoValue
abstract class SearchData {
  private String collegeName;
  private int collegeId;

  abstract SearchData(String collegeName, int collegeId) {
    this.collegeName = collegeName;
    this.collegeId = collegeId;
  }
}
