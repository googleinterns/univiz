package com.google.univiz.api.representation;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.univiz.common.MockCollegeData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class CollegeDataStatsConverterTest {

  private static final CollegeData NYU_COLLEGE_DATA = MockCollegeData.getNyuData();

  private final CollegeDataStatsConverter converter = new CollegeDataStatsConverter();

  @Test
  public void convertToCollegeStats() {
    CollegeStats stats = converter.convert(NYU_COLLEGE_DATA);

    assertThat(stats.admissionRate()).isEqualTo(NYU_COLLEGE_DATA.admissionRate());
    assertThat(stats.avgSat()).isEqualTo(NYU_COLLEGE_DATA.avgSat());
    assertThat(stats.numOfUndergrads()).isEqualTo(NYU_COLLEGE_DATA.numOfUndergrads());
    assertThat(stats.avgCost()).isEqualTo(NYU_COLLEGE_DATA.avgCost());
    assertThat(stats.ratioOfMen()).isEqualTo(NYU_COLLEGE_DATA.ratioOfMen());
    assertThat(stats.ratioOfWomen()).isEqualTo(NYU_COLLEGE_DATA.ratioOfWomen());
  }

  @Test
  public void convertBackToCollegeData() {
    CollegeStats stats =
        CollegeStats.builder()
            .setAdmissionRate(NYU_COLLEGE_DATA.admissionRate())
            .setAvgSat(NYU_COLLEGE_DATA.avgSat())
            .setNumOfUndergrads(NYU_COLLEGE_DATA.numOfUndergrads())
            .setAvgCost(NYU_COLLEGE_DATA.avgCost())
            .setRatioOfMen(NYU_COLLEGE_DATA.ratioOfMen())
            .setRatioOfWomen(NYU_COLLEGE_DATA.ratioOfWomen())
            .setName(NYU_COLLEGE_DATA.name())
            .build();

    assertThrows(UnsupportedOperationException.class, () -> converter.reverse().convert(stats));
  }
}
