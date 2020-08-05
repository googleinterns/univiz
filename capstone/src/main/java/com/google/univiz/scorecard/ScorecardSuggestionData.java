package com.google.univiz.scorecard;

import com.google.auto.value.Autovalue;
import com.google.gson.annotations.SerializedName;
import com.google.ryanharter.auto.value.gson.GenerateTypeAdapter;
import javax.annotation.Nullable;

@GenerateTypeAdapter
@Autovalue
abstract class ScorecardSuggestionData {

  @SerializedName("id")
  abstract int id();

  @SerializedName("school.name")
  @Nullable
  abstract String name();
}
