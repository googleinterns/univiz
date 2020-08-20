package com.google.univiz.api.representation;

import com.google.auto.value.AutoValue;

/** CollegeData is a class representing a single university/college/institution. */
@AutoValue
public abstract class CollegeData {

  public abstract CollegeId id();

  public abstract String name();

  public abstract String city();

  /**
   * Returns a boolean that tells whether the institution is the main campus or not, true or false.
   */
  public abstract boolean isMainCampus();

  public abstract double latitude();

  public abstract double longitude();

  /**
   * Returns an enum CarnegieSizeDegree that represents the institution's carnegie size degree, or
   * the highest degree certificate available at that institution, from not applicable to
   * exclusively graduate/professional.
   */
  public abstract CarnegieSizeDegree carnegieSizeDegree();

  public abstract double admissionRate();

  public abstract double avgSat();

  public abstract int numOfUndergrads();

  public abstract int avgCost();

  public abstract double ratioOfMen();

  public abstract double ratioOfWomen();

  public static Builder builder() {
    return new AutoValue_CollegeData.Builder()
        .setCarnegieSizeDegree(CarnegieSizeDegree.NOT_AVAILABLE)
        .setAvgSat(0.0)
        .setAdmissionRate(0.0);
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder setId(CollegeId value);

    public abstract Builder setName(String value);

    public abstract Builder setCity(String value);

    public abstract Builder setIsMainCampus(boolean value);

    public abstract Builder setLatitude(double value);

    public abstract Builder setLongitude(double value);

    public abstract Builder setCarnegieSizeDegree(CarnegieSizeDegree value);

    public abstract Builder setAdmissionRate(double value);

    public abstract Builder setAvgSat(double value);

    public abstract Builder setNumOfUndergrads(int value);

    public abstract Builder setAvgCost(int value);

    public abstract Builder setRatioOfMen(double value);

    public abstract Builder setRatioOfWomen(double value);

    public abstract CollegeData build();
  }
}
