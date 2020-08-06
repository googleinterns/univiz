package com.google.univiz.api;

import com.google.auto.value.AutoValue;

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
