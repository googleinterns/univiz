package com.google.univiz;

/**
 * CarnegieSizeDegree is a class that holds the enum for the range of values for a school's carnegie
 * size degree.
 */
public enum CarnegieSizeDegree {
  NOT_AVAILABLE("Degree unavailable"),
  SIZE1("Two-year college, very small"),
  SIZE2("Two-year college, small"),
  SIZE3("Two-year college, medium"),
  SIZE4("Two-year college, large"),
  SIZE5("Two-year college, very large"),
  SIZE6("Four-year college, very small and mostly nonresidential"),
  SIZE7("Four-year college, very small and mostly residential"),
  SIZE8("Four-year college, very small and residential"),
  SIZE9("Four-year college, small and mostly nonresidential"),
  SIZE10("Four-year college, small and mostly residential"),
  SIZE11("Four-year college, small and residential"),
  SIZE12("Four-year college, medium and mostly nonresidential"),
  SIZE13("Four-year college, medium and mostly residential"),
  SIZE14("Four-year college, medium and residential"),
  SIZE15("Four-year college, large and mostly nonresidential"),
  SIZE16("Four-year college, large and mostly residential"),
  SIZE17("Four-year college, large and residential"),
  SIZE18("Exclusively graduate and professial institution");

  private final String degree;
  private static CarnegieSizeDegree[] degreesList = CarnegieSizeDegree.values();

  CarnegieSizeDegree(String degree) {
    this.degree = degree;
  }

  public static CarnegieSizeDegree getDegree(int index) {
    return degreesList[index].degree;
  }
}
