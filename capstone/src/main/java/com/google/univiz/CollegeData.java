package com.google.univiz;

import com.google.auto.value.AutoValue;

/** CollegeData is a class representing a single university/college/institution. */
@AutoValue
abstract class CollegeData {

  abstract int id();

  abstract String name();

  abstract String city();

  /**
   * Returns a boolean that tells whether the institution is the main campus or not, true or false.
   */
  abstract boolean isMainCampus();

  abstract double latitude();

  abstract double longitude();

  /**
   * Returns an enum CarnegieSizeDegree that represents the institution's carnegie size degree, or
   * the highest degree certificate available at that institution, from not applicable to
   * exclusively graduate/professional.
   */
  abstract CarnegieSizeDegree carnegieSizeDegree();

  abstract double avgSat();

  abstract int numOfUndergrads();

  abstract int avgCost();

  abstract double ratioOfMen();

  abstract double ratioOfWomen();

  /**
   * The CollegeData builder has some set default values. The avgSat and admissionRate values are
   * estimated averages as of 2020.
   */
  static Builder builder() {
    return new AutoValue_CollegeData.Builder()
        .setCarnegieSizeDegree(NOT_AVAILABLE)
        .setAvgSat(1059.0)
        .setAdmissionRate(0.65);
  }

  @AutoValue.Builder
  abstract static class Builder {

    abstract Builder setId(int value);

    abstract Builder setName(String value);

    abstract Builder setCity(String value);

    abstract Builder setIsMainCampus(int value);

    abstract Builder setLatitude(double value);

    abstract Builder setLongitude(double value);

    abstract Builder setCarnegieSizeDegree(CarnegieSizeDegree value);

    abstract Builder setAdmissionRate(double value);

    abstract Builder setAvgSat(double value);

    abstract Builder setNumOfUndergrads(int value);

    abstract Builder setAvgCost(int value);

    abstract Builder setRatioOfMen(double value);

    abstract Builder setRatioOfWomen(double value);

    abstract CollegeData build();
  }
}
