package com.google.univiz;

import com.google.auto.value.AutoValue;

/**
 * ScorecardData is a class representing a single university, parsing the JSON data directly from
 * the CollegeScorecard API.
 */
@AutoValue
public abstract class ScorecardData {

  /**
   * Creates a new ScorecardData
   *
   * @param name The human-readable name for the school/college/university.
   * @param city The human-readable name for the city the university is located in.
   * @param latitude A float representing the school's location measured in latitude.
   * @param longitude A float representing the school's location measured in longitude.
   * @param flagMainCampus An integer that flags whether the university is the main campus or not, 1
   *     or 0.
   * @param urbanizationDegree An integer from range(1-8) that tells the degree of urbanization for
   *     that campus, from large city to rural.
   * @param admissionRate The overall rate of admission for the university.
   * @param avgSat The average overall SAT score for students accepted to the university.
   * @param numOfUndergrads The number of undergraduate students that attend the university.
   * @param avgCost The average cost of attendance for students at the university.
   * @param studentsInDebt The number of students in cumulative loan debt after graduation.
   * @param carnegieSizeDegree An integer from range(-2-18) that tells degree of Carnegie size, from
   *     not applicable to exclusively graduate/professional.
   * @param numOfMen A float that represents the number of men enrolled at the undergraduate level.
   * @param numOfWomen A float that represents the number of women enrolled at the undergradute
   *     level.
   */
  public static ScorecardData create(
      String name,
      String city,
      float latitude,
      float longitude,
      int flagMainCampus,
      int urbanizationDegree,
      float admissionRate,
      float avgSat,
      int numOfUndergrads,
      int avgCost,
      int studentsInDebt,
      int carnegieSizeDegree,
      float numOfMen,
      float numOfWomen) {
    return new AutoValue_ScorecardData(
        name,
        city,
        latitude,
        longitude,
        flagMainCampus,
        urbanizationDegree,
        admissionRate,
        avgSat,
        numOfUndergrads,
        avgCost,
        studentsInDebt,
        carnegieSizeDegree,
        numOfMen,
        numOfWomen);
  }

  public abstract String name();

  public abstract String city();

  public abstract float latitude();

  public abstract float longitude();

  public abstract int flagMainCampus();

  public abstract int urbanizationDegree();

  public abstract float admissionRate();

  public abstract float avgSat();

  public abstract int numOfUndergrads();

  public abstract int avgCost();

  public abstract int studentsInDebt();

  public abstract int carnegieSizeDegree();

  public abstract float numOfMen();

  public abstract float numOfWomen();
}
