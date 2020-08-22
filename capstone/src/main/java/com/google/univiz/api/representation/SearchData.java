package com.google.univiz.api.representation;

import com.google.auto.value.AutoValue;

/** SearchData represents the college data specific to the Search feature */
@AutoValue
public abstract class SearchData {
  public static SearchData create(String collegeName, CollegeId collegeId) {
    return new AutoValue_SearchData(collegeName, collegeId);
  }

  public abstract String collegeName();

  public abstract CollegeId collegeId();
}
