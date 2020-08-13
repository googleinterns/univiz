package com.google.univiz;

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
public final class SuggestionDataTest {
  /** Tests whether the JSON properly deserializes into a SuggestionData object */
  @Test
  public void testJsonDeserializes() throws Exception {
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY).create();
    InputStreamReader suggestionDataReader =
        new InputStreamReader(
            Resources.getResource(SuggestionDataTest.class, "suggestion_data.json").openStream());
    SuggestionData suggestionData = gson.fromJson(suggestionDataReader, SuggestionData.class);

    assertThat(suggestionData.name()).isEqualTo("Stanford University");
    assertThat(suggestionData.id()).isEqualTo(243744);
  }
}
