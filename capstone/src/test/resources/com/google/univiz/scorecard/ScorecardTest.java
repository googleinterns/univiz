package com.google.univiz.scorecard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class ScorecardTest {

  @Test
  public void parseJsonTest() {
    // TODO(biancamacias): insert sample JSON
    ScorecardData college = new Gson().fromJson(json, ScorecardData.class);

    asserThat(college.name().isEqualTo("New York University"));
    asserThat(college.city().isEqualTo("New York City"));
  }
}
