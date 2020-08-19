package com.google.univiz.api.representation;

import com.google.auto.value.AutoValue;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

/**
 * CollegeStats is a class that contains statistics for a particular college/university/institution.
 */
@GenerateTypeAdapter
@AutoValue
public abstract class CollegeStats {

  public abstract double admissionRate();

  public abstract double avgSat();

  public abstract int numOfUndergrads();

  public abstract int avgCost();

  public abstract double ratioOfMen();

  public abstract double ratioOfWomen();

  public static Builder builder() {
    return new AutoValue_CollegeStats.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder setAdmissionRate(double value);

    public abstract Builder setAvgSat(double value);

    public abstract Builder setNumOfUndergrads(int value);

    public abstract Builder setAvgCost(int value);

    public abstract Builder setRatioOfMen(double value);

    public abstract Builder setRatioOfWomen(double value);

    public abstract CollegeStats build();
  }
}
