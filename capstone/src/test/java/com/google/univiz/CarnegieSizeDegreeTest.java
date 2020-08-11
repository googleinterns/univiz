package com.google.univiz;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class CarnegieSizeDegreeTest {

  @Test
  public void testIndexing() {
    assertThat(CarnegieSizeDegree.getDegreeDescription(0)).isEqualTo("Degree unavailable");
    assertThat(CarnegieSizeDegree.getDegreeDescription(8))
        .isEqualTo("Four-year college, very small and residential");
    assertThat(CarnegieSizeDegree.getDegreeDescription(15))
        .isEqualTo("Four-year college, large and mostly nonresidential");
  }
}
