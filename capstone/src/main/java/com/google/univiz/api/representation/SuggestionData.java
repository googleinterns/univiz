package com.google.univiz.api.representation;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import javax.annotation.Nullable;

/** SuggestionData represents the college data specific to the Search feature */
@GenerateTypeAdapter
@AutoValue
public abstract class SuggestionData {
  @SerializedName("school.name")
  @Nullable
  public abstract String name();

  public abstract int id();

  public static SuggestionData create(String name, int id) {
    return new AutoValue_SuggestionData(name, id);
  }
}
