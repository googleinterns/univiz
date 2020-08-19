package com.google.univiz.scorecard;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.List;

/** SuggestionResponse represents the list of SuggestionData specific for the Search feature */
@GenerateTypeAdapter
@AutoValue
public abstract class SuggestionResponse {
  @SerializedName("results")
  public abstract List<SuggestionData> suggestions();
}
