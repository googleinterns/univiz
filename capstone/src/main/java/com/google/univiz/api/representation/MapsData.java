package com.google.univiz.api.representation;

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
  abstract static class Builder {

    abstract Builder setName(String value);

    abstract Builder setCity(String city);

    abstract Builder setIsMainCampus(boolean value);

    abstract Builder setLatitude(double value);

    abstract Builder setLongitude(double value);

    abstract MapsData build();
  }
}
