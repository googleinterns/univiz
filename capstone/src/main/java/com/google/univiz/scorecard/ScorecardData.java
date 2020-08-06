package com.google.univiz.scorecard;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import javax.annotation.Nullable;

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

  /**
   * Returns an integer representing the unique ID for the institution via the CollegeScorecard API.
   */
  @SerializedName("id")
  abstract int id();

  /** Returns the human-readable name for the institution. */
  @SerializedName("school.name")
  @Nullable
  abstract String name();

  /** Returns human-readable name for the city the institution is located in. */
  @SerializedName("school.city")
  @Nullable
  abstract String city();

  /** Returns an integer that flags whether the institution is the main campus or not, 1 or 0. */
  @SerializedName("school.main_campus")
  abstract int flagMainCampus();

  /**
   * Returns an integer from range [1, 8] that tells the degree of urbanization for institution's
   * campus, from large city to rural.
   */
  @SerializedName("school.degree_urbanization")
  abstract int urbanizationDegree();

  /** Returns a double representing the institution's latitude location measured in degrees. */
  @SerializedName("location.lat")
  abstract double latitude();

  /** Returns a double representing the institution's longitude location measured in degrees. */
  @SerializedName("location.lon")
  abstract double longitude();

  /**
   * Returns an integer from range [-2, 18] that tells degree of Carnegie size, from not applicable
   * to exclusively graduate/professional.
   */
  @SerializedName("school.carnegie_size_setting")
  abstract int carnegieSizeDegree();

  /** Returns a double representing the overall rate of admission for the institution. */
  @SerializedName("latest.admissions.admission_rate.overall")
  abstract double admissionRate();

  /**
   * Returns a double representing the average overall SAT score for students accepted to the
   * institution.
   */
  @SerializedName("latest.admissions.sat_scores.average.overall")
  abstract double avgSat();

  /**
   * Returns an integer representing the number of enrolled undergraduate certificate/degree-seeking
   * students at the institution.
   */
  @SerializedName("latest.student.size")
  abstract int numOfUndergrads();

  /** Returns the average cost of attendance for students per academic year at the institution. */
  @SerializedName("latest.cost.attendance.academic_year")
  abstract int avgCost();

  /** Returns a double that represents the ratio of men enrolled at the undergraduate level. */
  @SerializedName("latest.student.demographics.men")
  abstract double ratioOfMen();

  /** Returns a double that represents the ratio of women enrolled at the undergraduate level. */
  @SerializedName("latest.student.demographics.women")
  abstract double ratioOfWomen();
}
