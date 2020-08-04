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
   * Dictionary (https://collegescorecard.ed.gov/assets/CollegeScorecardDataDictionary.xlsx), which
   * outlines the type and description of all available data elements. The parameters used here are
   * a select few data elements the API provides.
   */

  /** @return The human-readable name for the school/college/university. */
  @SerializedName("school.name")
  abstract String name();

  /** @return The human-readable name for the city the university is located in. */
  @SerializedName("school.city")
  abstract String city();

  /** @return An integer that flags whether the university is the main campus or not, 1 or 0. */
  @SerializedName("school.main_campus")
  abstract int flagMainCampus();

  /**
   * @return An integer from range(1-8) that tells the degree of urbanization for that campus, from
   *     large city to rural.
   */
  @SerializedName("school.degree_urbanization")
  abstract int urbanizationDegree();

  /** @return A float representing the school's location measured in latitude. */
  @SerializedName("location.lat")
  abstract float latitude();

  /** @return A float representing the school's location measured in longitude. */
  @SerializedName("location.lon")
  abstract float longitude();

  /**
   * @return An integer from range(-2-18) that tells degree of Carnegie size, from not applicable to
   *     exclusively graduate/professional.
   */
  @SerializedName("school.carnegie_size_setting")
  abstract int carnegieSizeDegree();

  /** @return The overall rate of admission for the university. */
  @SerializedName("admissions.admission_rate.overall")
  abstract float admissionRate();

  /** @return The average overall SAT score for students accepted to the university. */
  @SerializedName("admissions.sat_scores.average.overall")
  abstract float avgSat();

  /** @return The number of undergraduate students that are enrolled the university. */
  @SerializedName("enrollment.all")
  abstract int numOfUndergrads();

  /** @return The average cost of attendance for students per academic year at the university. */
  @SerializedName("cost.attendance.academic_year")
  abstract int avgCost();

  /** @return The number of students in cumulative loan debt after graduation. */
  @SerializedName("aid.cumulative_debt_number")
  abstract int studentsInDebt();

  /** @return A float that represents the number of men enrolled at the undergraduate level. */
  @SerializedName("student.demographics.men")
  abstract float numOfMen();

  /** @return A float that represents the number of women enrolled at the undergradute level. */
  @SerializedName("student.demographics.women")
  abstract float numOfWomen();
}
