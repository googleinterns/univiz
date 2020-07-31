package com.google.univiz.config;

import com.google.auto.value.AutoValue;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

@AutoValue
@GenerateTypeAdapter
/** Contains configuration parameters for the Univiz application. */
public abstract class UnivizConfig {
  public abstract String scorecardApiKey();

  static UnivizConfig create(String scorecardApiKey) {
    return new AutoValue_UnivizConfig(scorecardApiKey);
  }
}
