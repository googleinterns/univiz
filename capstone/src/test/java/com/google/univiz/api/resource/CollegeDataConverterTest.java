package com.google.univiz.api.resource;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.univiz.api.representation.CollegeData;
import com.google.univiz.api.representation.MapsData;
import com.google.univiz.common.MockCollegeData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class CollegeDataConverterTest {

  private static final CollegeData NYU_COLLEGE_DATA = MockCollegeData.getNyuData();

  private final CollegeDataConverter converter = new CollegeDataConverter();

  @Test
  public void convertToMapsData() throws Exception {
    MapsData mapData = converter.convert(NYU_COLLEGE_DATA);

    assertThat(mapData.name()).isEqualTo(NYU_COLLEGE_DATA.name());
    assertThat(mapData.city()).isEqualTo(NYU_COLLEGE_DATA.city());
    assertThat(mapData.isMainCampus()).isEqualTo(NYU_COLLEGE_DATA.isMainCampus());
    assertThat(mapData.latitude()).isEqualTo(NYU_COLLEGE_DATA.latitude());
    assertThat(mapData.longitude()).isEqualTo(NYU_COLLEGE_DATA.longitude());
  }

  @Test
  public void convertBackToCollegeData() {
    MapsData mapData =
        MapsData.builder()
            .setName(NYU_COLLEGE_DATA.name())
            .setCity(NYU_COLLEGE_DATA.city())
            .setIsMainCampus(NYU_COLLEGE_DATA.isMainCampus())
            .setLatitude(NYU_COLLEGE_DATA.latitude())
            .setLongitude(NYU_COLLEGE_DATA.longitude())
            .build();

    assertThrows(UnsupportedOperationException.class, () -> converter.reverse().convert(mapData));
  }
}
