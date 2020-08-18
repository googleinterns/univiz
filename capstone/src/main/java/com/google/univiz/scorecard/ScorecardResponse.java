package com.google.univiz.scorecard;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.List;

/**
 * ScorecardResponse is a class representing a list of ScorecardData types. This class parses the
 * JSON by only taking the JSON data from the "results", which is the array that holds the
 * college/university/institution data needed for the UniViz app.
 */
@GenerateTypeAdapter
@AutoValue
abstract class ScorecardResponse {

  @SerializedName("results")
  abstract List<ScorecardData> scorecardData();
}
