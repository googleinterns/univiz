package com.google.univiz.api.representation;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.io.InputStreamReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class MetadataReaderTest {
  /** Tests whether the JSON properly deserializes into the MetadataReader object correctly */
  @Test
  public void testJsonDeserializes() throws Exception {
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY).create();
    InputStreamReader metadataReaderStream =
        new InputStreamReader(
            Resources.getResource(MetadataReaderTest.class, "suggestion-response.json")
                .openStream());
    MetadataReader metadataReader =
        gson.fromJson(metadataReaderStream, MetadataReader.class);

    assertThat(metadataReader.content().total()).isEqualTo(1);
    assertThat(metadataReader.content().pgnum()).isEqualTo(0);
    assertThat(metadataReader.content().perpage()).isEqualTo(100);
  }
}
