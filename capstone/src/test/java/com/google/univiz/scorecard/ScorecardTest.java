package com.google.univiz.scorecard;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.io.IOException;
import java.io.InputStreamReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class ScorecardTest {

  private ScorecardData scorecardData;

  @Before
  public void setUp() throws IOException {
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY).create();
    try (InputStreamReader scorecardReader =
        new InputStreamReader(Resources.getResource("scorecard.json").openStream())) {
      return gson.fromJson(scorecardReader, ScorecardData.class);
    } catch (IOException exception) {
      throw new AssertionError("Could not load Scorecard API Data", exception);
    }
  }

  @Test
  public void testNameDeserializes() {
    assertThat(scorecardData.name().isEqualTo("New York University"));
  }
}
