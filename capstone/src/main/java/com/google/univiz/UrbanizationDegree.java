package com.google.univiz;

/**
 * UrbanizationDegree is a class that holds the enum for the range of values for a school's
 * urbanization degree. The index of each of these values is the urbanization degree provided by
 * ScorecardData.
 */
public enum UrbanizationDegree {
  NOT_AVAILABLE("Degree Unavailable"),
  LARGE_CITY("Large city"),
  MIDSIZE_CITY("Midsize city"),
  URBAN_LARGE_CITY("Urban large city"),
  URBAN_MIDSIZE_CITY("Urban midsize city"),
  LARGE_TOWN("Large town"),
  SMALL_TOWN("Small town"),
  RURAL("Rural"),
  RURAL_MSA("Rural");

  private final String degree;

  UrbanizationDegree(String degree) {
    this.degree = degree;
  }
}
