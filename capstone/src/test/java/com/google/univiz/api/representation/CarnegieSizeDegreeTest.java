package com.google.univiz.api.representation;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class CarnegieSizeDegreeTest {

  @Test
  public void testIndexing() {
    assertThat(CarnegieSizeDegree.NOT_AVAILABLE).isEqualTo(CarnegieSizeDegree.getDegree(0));
    assertThat(CarnegieSizeDegree.VSMALL_NONRESI_FOUR_YEAR_COLLEGE)
        .isEqualTo(CarnegieSizeDegree.getDegree(6));
    assertThat(CarnegieSizeDegree.SMALL_TWO_YEAR_COLLEGE)
        .isEqualTo(CarnegieSizeDegree.getDegree(2));
  }
}
