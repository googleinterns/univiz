package com.google.univiz;

import com.google.auto.value.AutoValue;

/**
 * CollegeData is a class representing a single university/college/institution. These parameters use
 * data parsed from the CollegeScorecard API. This data has been converted from the ScorecardData
 * class in order to create usable, readable accessor methods for the Univiz web application.
 */
@AutoValue
abstract class CollegeData {

  abstract int id();

  abstract String name();

  abstract String city();

  /**
   * Returns a boolean that tells whether the institution is the main campus or not, true or false.
   */
  abstract boolean isMainCampus();

  /**
   * Returns a string that describes the institution's degree of urbanzation, or how populated the
   * surrounding areas are around the institution, from large city to rural.
   */
  abstract String urbanizationDegree();

  abstract float latitude();

  abstract float longitude();

  /**
   * Returns a string that represents the institution's carnegie size degree, or the highest degree
   * certificate available at that institution, from not applicable to exclusively
   * graduate/professional.
   */
  abstract String carnegieSizeDegree();

  abstract float avgSat();

  abstract int numOfUndergrads();

  abstract int avgCost();

  abstract float menRatio();

  abstract float womenRatio();
}
