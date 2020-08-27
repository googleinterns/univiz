package com.google.univiz.api.representation;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.List;

@GenerateTypeAdapter
@AutoValue
public abstract class MetadataReader {
  @SerializedName("metadata")
  public abstract MetaInfo content();

  public static MetadataReader create(MetaInfo content) {
    return new AutoValue_MetadataReader(content);
  }
}
