package com.google.univiz.scorecard;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;

/**
 * ScorecardData is a class representing a single university/college/institution from the parsed
 * JSON data directly from the CollegeScorecard API.
 */
@AutoValue
public abstract class ScorecardData {

  /**
   * Creates a new ScorecardData
   *
   * @param name The human-readable name for the school/college/university.
   * @param city The human-readable name for the city the university is located in.
   * @param flagMainCampus An integer that flags whether the university is the main campus or not, 1
   *     or 0.
   * @param urbanizationDegree An integer from range(1-8) that tells the degree of urbanization for
   *     that campus, from large city to rural.
   * @param latitude A float representing the school's location measured in latitude.
   * @param longitude A float representing the school's location measured in longitude.
   * @param carnegieSizeDegree An integer from range(-2-18) that tells degree of Carnegie size, from
   *     not applicable to exclusively graduate/professional.
   * @param admissionRate The overall rate of admission for the university.
   * @param avgSat The average overall SAT score for students accepted to the university.
   * @param numOfUndergrads The number of undergraduate students that are enrolled the university.
   * @param avgCost The average cost of attendance for students per academic year at the university.
   * @param studentsInDebt The number of students in cumulative loan debt after graduation.
   * @param numOfMen A float that represents the number of men enrolled at the undergraduate level.
   * @param numOfWomen A float that represents the number of women enrolled at the undergradute
   *     level.
   */
  public static ScorecardData create(
      String name,
      String city,
      int flagMainCampus,
      int urbanizationDegree,
      float latitude,
      float longitude,
      int carnegieSizeDegree,
      float admissionRate,
      float avgSat,
      int numOfUndergrads,
      int avgCost,
      int studentsInDebt,
      float numOfMen,
      float numOfWomen) {
    return new AutoValue_ScorecardData(
        name,
        city,
        flagMainCampus,
        urbanizationDegree,
        latitude,
        longitude,
        carnegieSizeDegree,
        admissionRate,
        avgSat,
        numOfUndergrads,
        avgCost,
        studentsInDebt,
        numOfMen,
        numOfWomen);
  }

  @SerializedName("school.name")
  public abstract String name();

  @SerializedName("school.city")
  public abstract String city();

  @SerializedName("school.main_campus")
  public abstract int flagMainCampus();

  @SerializedName("school.degree_urbanization")
  public abstract int urbanizationDegree();

  @SerializedName("location.lat")
  public abstract float latitude();

  @SerializedName("location.lon")
  public abstract float longitude();

  @SerializedName("school.carnegie_size_setting")
  public abstract int carnegieSizeDegree();

  @SerializedName("admissions.admission_rate.overall")
  public abstract float admissionRate();

  @SerializedName("admissions.sat_scores.average.overall")
  public abstract float avgSat();

  @SerializedName("enrollment.all")
  public abstract int numOfUndergrads();

  @SerializedName("cost.attendance.academic_year")
  public abstract int avgCost();

  @SerializedName("aid.cumulative_debt_number")
  public abstract int studentsInDebt();

  @SerializedName("student.demographics.men")
  public abstract float numOfMen();

  @SerializedName("student.demographics.women")
  public abstract float numOfWomen();
}
