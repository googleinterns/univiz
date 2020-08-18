package com.google.univiz.api;

import com.google.auto.value.AutoValue;

/** SearchData represents the college data specific to the Search feature */
@AutoValue
public abstract class SearchData {
  static SearchData create(String collegeName, CollegeId collegeId) {
    return new AutoValue_SearchData(collegeName, collegeId);
  }

  public abstract String getCollegeName();

  public abstract CollegeId getCollegeId();
}
