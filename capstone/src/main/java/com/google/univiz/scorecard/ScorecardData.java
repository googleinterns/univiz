package com.google.univiz.scorecard;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

/**
 * ScorecardData is a class representing a single university/college/institution from the parsed
 * JSON data directly from the CollegeScorecard API. The parameters come directly from the
 * CollegeScorecard API Data Dictionary
 * (https://collegescorecard.ed.gov/assets/CollegeScorecardDataDictionary.xlsx), which outlines the
 * type and description of all available data elements. The parameters used here are a select few
 * data elements the API provides.
 */
@GenerateTypeAdapter
@AutoValue
abstract class ScorecardData {

  /** Returns the human-readable name for the school/college/university. */
  @SerializedName("school.name")
  abstract String name();

  /** Returns human-readable name for the city the institution is located in. */
  @SerializedName("school.city")
  abstract String city();

  /** Returns an integer that flags whether the institution is the main campus or not, 1 or 0. */
  @SerializedName("school.main_campus")
  abstract int flagMainCampus();

  /**
   * Returns an integer from range [1-8] that tells the degree of urbanization for institution's campus, from
   *     large city to rural.
   */
  @SerializedName("school.degree_urbanization")
  abstract int urbanizationDegree();

  /** Returns a float representing the institution's latitude location measured in degrees. */
  @SerializedName("location.lat")
  abstract float latitude();

  /** Returns a float representing the institution's longitude location measured in degrees. */
  @SerializedName("location.lon")
  abstract float longitude();

  /**
   * Returns an integer from range [-2-18] that tells degree of Carnegie size, from not applicable to
   *     exclusively graduate/professional.
   */
  @SerializedName("school.carnegie_size_setting")
  abstract int carnegieSizeDegree();

  /** Returns a float representing the overall rate of admission for the institution. */
  @SerializedName("2018.admissions.admission_rate.overall")
  abstract float admissionRate();

  /** Returns a float representing the average overall SAT score for students accepted to the institution. */
  @SerializedName("2018.admissions.sat_scores.average.overall")
  abstract float avgSat();

  /** Returns an integer representing the number of enrolled undergraduate certificate/degree-seeking students at the institution. */
  @SerializedName("2018.student.size")
  abstract int numOfUndergrads();

  /** Returns the average cost of attendance for students per academic year at the institution. */
  @SerializedName("2018.cost.attendance.academic_year")
  abstract int avgCost();

  /** Returns a float that represents the ratio of men enrolled at the undergraduate level. */
  @SerializedName("2018.student.demographics.men")
  abstract float numOfMen();

  /** Returns a float that represents the ratio of women enrolled at the undergraduate level. */
  @SerializedName("2018.student.demographics.women")
  abstract float numOfWomen();
}
