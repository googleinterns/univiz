package com.google.univiz.api;

import com.google.auto.value.AutoValue;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

/**
 * MapsData is a class representing all relevant college data that is specific to the Maps feature.
 */
@GenerateTypeAdapter
@AutoValue
public abstract class MapsData {

  public abstract String name();

  public abstract String city();

  public abstract boolean isMainCampus();

  public abstract double latitude();

  public abstract double longitude();

  public static Builder builder() {
    return new AutoValue_MapsData.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder setName(String value);

    public abstract Builder setCity(String city);

    public abstract Builder setIsMainCampus(boolean value);

    public abstract Builder setLatitude(double value);

    public abstract Builder setLongitude(double value);

    public abstract MapsData build();
  }
}
