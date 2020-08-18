package com.google.univiz.scorecard;

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

  @SerializedName("id")
  public abstract int id();
}
