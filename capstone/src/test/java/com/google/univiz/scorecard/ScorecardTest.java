package com.google.univiz.scorecard;

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
public final class ScorecardTest {

  @Test
  public void testNameDeserializes() {
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY).create();
    InputStreamReader scorecardReader =
        new InputStreamReader(
            Resources.getResource(ScorecardTest.class, "scorecard.json").openStream());
    ScorecardData scorecardData = gson.fromJson(scorecardReader, ScorecardData.class);
    assertThat(scorecardData.name()).isEqualTo("New York University");
  }
}
