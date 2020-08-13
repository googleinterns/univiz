package com.google.univiz.api.dataviz;

import com.google.univiz.CarnegieSizeDegree;
import com.google.auto.value.AutoValue;

/**
 * Contains statistics related to tuition for a particular college. The following will be included:
 * average tuition cost before and after financial aid is considered, and tuition cost over the last
 * five years.
 */
@AutoValue
public abstract class TuitionStats {

  public abstract CarnegieSizeDegree carnegieSizeDegree();

  public abstract double admissionRate();

  public abstract double avgSat();

  public abstract int numOfUndergrads();

  public abstract int avgCost();

  public abstract double ratioOfMen();

  public abstract double ratioOfWomen();

  public static Builder builder() {
    return new AutoValue_TuitionStats.Builder()
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder setCarnegieSizeDegree(CarnegieSizeDegree value);

    public abstract Builder setAdmissionRate(double value);

    public abstract Builder setAvgSat(double value);

    public abstract Builder setNumOfUndergrads(int value);

    public abstract Builder setAvgCost(int value);

    public abstract Builder setRatioOfMen(double value);

    public abstract Builder setRatioOfWomen(double value);

    public abstract TuitionStats build();
  }
}
