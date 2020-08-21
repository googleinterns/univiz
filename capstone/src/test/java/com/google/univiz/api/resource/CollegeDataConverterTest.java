package com.google.univiz.api.resource;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.univiz.api.representation.CarnegieSizeDegree;
import com.google.univiz.api.representation.CollegeData;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.MapsData;
import com.google.univiz.common.MockCollegeData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class CollegeDataConverterTest {

  private static final CollegeData nyu = MockCollegeData.getNyuData();

  private final CollegeDataConverter converter = new CollegeDataConverter();

  @Test
  public void convertToMapsData() throws Exception {
    MapsData mapData = converter.convert(nyu);

    assertThat(mapData.name()).isEqualTo(nyu.name());
    assertThat(mapData.city()).isEqualTo(nyu.city());
    assertThat(mapData.isMainCampus()).isEqualTo(nyu.isMainCampus());
    assertThat(mapData.latitude()).isEqualTo(nyu.latitude());
    assertThat(mapData.longitude()).isEqualTo(nyu.longitude());
  }

  @Test
  public void convertBackToCollegeData() {
    MapsData mapData =
        MapsData.builder()
            .setName(nyu.name())
            .setCity(nyu.city())
            .setIsMainCampus(nyu.isMainCampus())
            .setLatitude(nyu.latitude())
            .setLongitude(nyu.longitude())
            .build();

    assertThrows(UnsupportedOperationException.class, () -> converter.reverse().convert(mapData));
  }
}
