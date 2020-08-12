package com.google.univiz.scorecard;

import com.google.auto.value.AutoValue;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.List;

@GenerateTypeAdapter
@AutoValue
abstract class ScorecardResponse {
  
  @SeralizedName("results")
  abstract List<ScorecardData> scorecardData();
}
