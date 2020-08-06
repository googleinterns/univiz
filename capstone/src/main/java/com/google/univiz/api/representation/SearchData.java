package com.google.univiz.api.representation;

import com.google.auto.value.AutoValue;

/** SearchData represents the college data specific to the Search feature */
@AutoValue
public abstract class SearchData {
  private String collegeName;
  private int collegeId;

  public static SearchData create(String collegeName, int collegeId) {
    this.collegeName = collegeName;
    this.collegeId = collegeId;
    return new AutoValue_SearchData(collegeName, collegeId);
  }

  public abstract String getCollegeName();

  public abstract int getCollegeId();
}
