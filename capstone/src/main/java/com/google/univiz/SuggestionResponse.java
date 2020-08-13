package com.google.univiz;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.List;

/** SuggestionResponse represents the list SuggestionData specific to the Search feature */
@GenerateTypeAdapter
@AutoValue
abstract class SuggestionResponse {
  @SerializedName("results")
  abstract List<SuggestionData> suggestionData();
}
