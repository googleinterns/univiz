package com.google.univiz;

/**
 * CarnegieSizeDegree is a class that holds the enum for the range of values for a school's carnegie
 * size degree.
 */
public enum CarnegieSizeDegree {
  NOT_AVAILABLE("Degree unavailable"),
  VSMALL_TWO_YEAR_COLLEGE("Two-year college, very small"),
  SMALL_TWO_YEAR_COLLEGE("Two-year college, small"),
  MEDIUM_TWO_YEAR_COLLEGE("Two-year college, medium"),
  LARGE_TWO_YEAR_COLLEGE("Two-year college, large"),
  VLARGE_TWO_YEAR_COLLEGE("Two-year college, very large"),
  VSMALL_NONRESI_FOUR_YEAR_COLLEGE("Four-year college, very small and mostly nonresidential"),
  VSMALL_MOSTLY_RESI_FOUR_YEAR_COLLEGE("Four-year college, very small and mostly residential"),
  VSMALL_RESI_FOUR_YEAR_COLLEGE("Four-year college, very small and residential"),
  SMALL_NONRESI_FOUR_YEAR_COLLEGE("Four-year college, small and mostly nonresidential"),
  SMALL_MOSTLY_RESI_FOUR_YEAR_COLLEGE("Four-year college, small and mostly residential"),
  SMALL_RESI_FOUR_YEAR_COLLEGE("Four-year college, small and residential"),
  MEDIUM_NONRESI_FOUR_YEAR_COLLEGE("Four-year college, medium and mostly nonresidential"),
  MEDIUM_MOSTLY_RESI_FOUR_YEAR_COLLEGE("Four-year college, medium and mostly residential"),
  MEDIUM_RESI_FOUR_YEAR_COLLEGE("Four-year college, medium and residential"),
  LARGE_NONRESI_FOUR_YEAR_COLLEGE("Four-year college, large and mostly nonresidential"),
  LARGE_MOSTLY_RESI_FOUR_YEAR_COLLEGE("Four-year college, large and mostly residential"),
  LARGE_RESI_FOUR_YEAR_COLLEGE("Four-year college, large and residential"),
  GRADUTE_PROFESSIONAL_COLLEGE("Exclusively graduate and professial institution");

  private final String degree;

  CarnegieSizeDegree(String degree) {
    this.degree = degree;
  }
}
