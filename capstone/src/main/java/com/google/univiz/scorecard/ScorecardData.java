package com.google.univiz.scorecard;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.google.gson.TypeAdapter;

/**
 * ScorecardData is a class representing a single university/college/institution from the parsed
 * JSON data directly from the CollegeScorecard API.
 */
@GenerateTypeAdapter
@AutoValue
abstract class ScorecardData {

  /**
   * Creates a new ScorecardData. The parameters come directly from the CollegeScorecard API Data
   * Dictionary, which outlines the type and description of all available data elements. The
   * parameters used here are a select few data elements the API provides.
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
  private static ScorecardData create(
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
  abstract String name();

  @SerializedName("school.city")
  abstract String city();

  @SerializedName("school.main_campus")
  abstract int flagMainCampus();

  @SerializedName("school.degree_urbanization")
  abstract int urbanizationDegree();

  @SerializedName("location.lat")
  abstract float latitude();

  @SerializedName("location.lon")
  abstract float longitude();

  @SerializedName("school.carnegie_size_setting")
  abstract int carnegieSizeDegree();

  @SerializedName("admissions.admission_rate.overall")
  abstract float admissionRate();

  @SerializedName("admissions.sat_scores.average.overall")
  abstract float avgSat();

  @SerializedName("enrollment.all")
  abstract int numOfUndergrads();

  @SerializedName("cost.attendance.academic_year")
  abstract int avgCost();

  @SerializedName("aid.cumulative_debt_number")
  abstract int studentsInDebt();

  @SerializedName("student.demographics.men")
  abstract float numOfMen();

  @SerializedName("student.demographics.women")
  abstract float numOfWomen();
}
