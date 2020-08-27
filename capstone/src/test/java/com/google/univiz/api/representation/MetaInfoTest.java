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
public final class MetaInfoTest {
  /** Tests whether the JSON properly deserializes into a MetaInfo object */
  @Test
  public void testJsonDeserializes() throws Exception {
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY).create();
    InputStreamReader metaInfoReader =
        new InputStreamReader(
            Resources.getResource(MetaInfoTest.class, "meta-info.json").openStream());
    MetaInfo metaInfo = gson.fromJson(metaInfoReader, MetaInfo.class);

    assertThat(metaInfo.total()).isEqualTo(0);
    assertThat(metaInfo.pgnum()).isEqualTo(0);
    assertThat(metaInfo.perpage()).isEqualTo(100);
  }
}
