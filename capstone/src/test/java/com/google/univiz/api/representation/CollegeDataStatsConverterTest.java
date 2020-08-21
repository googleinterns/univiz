package com.google.univiz.api.representation;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.univiz.common.MockCollegeData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class CollegeDataStatsConverterTest {

  private static CollegeData nyu = MockCollegeData.getNyuData();

  private final CollegeDataStatsConverter converter = new CollegeDataStatsConverter();

  @Test
  public void convertToCollegeStats() {
    CollegeStats stats = converter.convert(nyu);

    assertThat(stats.admissionRate()).isEqualTo(nyu.admissionRate());
    assertThat(stats.avgSat()).isEqualTo(nyu.avgSat());
    assertThat(stats.numOfUndergrads()).isEqualTo(nyu.numOfUndergrads());
    assertThat(stats.avgCost()).isEqualTo(nyu.avgCost());
    assertThat(stats.ratioOfMen()).isEqualTo(nyu.ratioOfMen());
    assertThat(stats.ratioOfWomen()).isEqualTo(nyu.ratioOfWomen());
  }

  @Test
  public void convertBackToCollegeData() {
    CollegeStats stats =
        CollegeStats.builder()
            .setAdmissionRate(nyu.admissionRate())
            .setAvgSat(nyu.avgSat())
            .setNumOfUndergrads(nyu.numOfUndergrads())
            .setAvgCost(nyu.avgCost())
            .setRatioOfMen(nyu.ratioOfMen())
            .setRatioOfWomen(nyu.ratioOfWomen())
            .build();

    assertThrows(UnsupportedOperationException.class, () -> converter.reverse().convert(stats));
  }
}
