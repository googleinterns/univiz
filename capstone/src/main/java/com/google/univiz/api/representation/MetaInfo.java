package com.google.univiz.api.representation;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

/** SuggestionData represents the college data specific to the Search feature */
@GenerateTypeAdapter
@AutoValue
public abstract class MetaInfo {
  @SerializedName("total")
  public abstract int total();

  @SerializedName("page")
  public abstract int pgnum();

  @SerializedName("per_page")
  public abstract int perpage();

  public static MetaInfo create(int total, int pgnum, int perpage) {
    return new AutoValue_MetaInfo(total, pgnum, perpage);
  }
}
