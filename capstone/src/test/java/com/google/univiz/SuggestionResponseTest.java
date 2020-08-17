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
public final class SuggestionResponseTest {
  /** Tests whether the JSON properly deserializes into the SuggestionResponse object correctly */
  @Test
  public void testJsonDeserializes() throws Exception {
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY).create();
    InputStreamReader suggestionResponseReader =
        new InputStreamReader(
            Resources.getResource(SuggestionResponseTest.class, "suggestion-response.json")
                .openStream());
    SuggestionResponse suggestionResponse =
        gson.fromJson(suggestionResponseReader, SuggestionResponse.class);

    assertThat(suggestionResponse.suggestionData()).hasSize(1);
    assertThat(suggestionResponse.suggestionData().get(0).name()).isEqualTo("Stanford University");
    assertThat(suggestionResponse.suggestionData().get(0).id()).isEqualTo(243744);
  }
}
