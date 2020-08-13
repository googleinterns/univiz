package com.google.univiz;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import javax.annotation.Nullable;

/** SuggestionData represents the college data specific to the Search feature */
@GenerateTypeAdapter
@AutoValue
abstract class SuggestionData {
  /**
   * TODO: JavaDoc
   */
  @SerializedName("school.name")
  @Nullable
  abstract String name();

  /**
   * TODO: JavaDoc
   */
  @SerializedName("id")
  abstract int id();
}