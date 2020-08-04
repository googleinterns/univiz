package com.google.univiz.scorecard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStreamReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class ScorecardTest {

  @Test
  public void parseJsonTest() {
    // TODO(biancamacias): insert sample JSON
    String json = "{}";
    ScorecardData college = new Gson().fromJson(json, ScorecardData.class);

    asserThat(college.name().isEqualTo("New York University"));
    asserThat(college.city().isEqualTo("New York City"));
  }
}
