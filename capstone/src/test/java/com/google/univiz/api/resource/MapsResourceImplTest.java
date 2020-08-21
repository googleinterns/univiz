package com.google.univiz.api.resource;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.univiz.api.CollegeDataApi;
import com.google.univiz.api.representation.CarnegieSizeDegree;
import com.google.univiz.api.representation.CollegeData;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.MapsData;
import com.google.univiz.common.MockCollegeData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(JUnit4.class)
public final class MapsResourceImplTest {

  @Rule public final MockitoRule rule = MockitoJUnit.rule();

  @Bind @Mock private CollegeDataApi collegeDataApi;

  @Inject private MapsResourceImpl mapsImpl;

  private final CollegeData nyu = MockCollegeData.getNyuData();

  private static final CollegeId STANFORD_COLLEGE_ID = CollegeId.create(243744);
  private static final String STANDFORD_NAME = "Stanford University";
  private static final double STANFORD_LATITUDE = 37.429434;

  private static final CollegeData stanford =
      CollegeData.builder()
          .setId(STANFORD_COLLEGE_ID)
          .setName(STANDFORD_NAME)
          .setCity("Stanford")
          .setIsMainCampus(true)
          .setLatitude(STANFORD_LATITUDE)
          .setLongitude(-122.167359)
          .setCarnegieSizeDegree(CarnegieSizeDegree.getDegree(17))
          .setAdmissionRate(0.0436)
          .setAvgSat(1497.0)
          .setNumOfUndergrads(7083)
          .setAvgCost(69109)
          .setRatioOfMen(0.4991)
          .setRatioOfWomen(0.5009)
          .build();

  @Before
  public void guiceSetUp() {
    Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void testMapsResourceImpl() throws IOException {
    List<CollegeId> ids = Lists.newArrayList(nyu.id());
    List<CollegeData> collegesData = Lists.newArrayList(nyu);

    when(collegeDataApi.getCollegesById(ids)).thenReturn(collegesData);
    List<MapsData> mapsData = mapsImpl.getMapsData(ids);
    assertThat(mapsData).hasSize(1);

    MapsData collegeMapData = mapsData.get(0);
    assertThat(collegeMapData.name()).isEqualTo(nyu.name());
    assertThat(collegeMapData.latitude()).isEqualTo(nyu.latitude());
  }

  @Test
  public void testEmptyMapsResourcesImpl() throws IOException {
    List<CollegeId> ids = new ArrayList<>();
    List<CollegeData> collegesData = new ArrayList<>();

    when(collegeDataApi.getCollegesById(ids)).thenReturn(collegesData);
    List<MapsData> mapsData = mapsImpl.getMapsData(ids);
    assertThat(mapsData).isEmpty();
  }

  @Test
  public void testManyMapsResourcesImpl() throws IOException {
    List<CollegeId> ids = Lists.newArrayList(nyu.id(), STANFORD_COLLEGE_ID);
    List<CollegeData> collegesData = Lists.newArrayList(nyu, stanford);

    when(collegeDataApi.getCollegesById(ids)).thenReturn(collegesData);
    List<MapsData> mapsData = mapsImpl.getMapsData(ids);
    assertThat(mapsData).hasSize(2);

    MapsData college1MapData = mapsData.get(0);
    MapsData college2MapData = mapsData.get(1);
    assertThat(college1MapData.name()).isEqualTo(nyu.name());
    assertThat(college1MapData.latitude()).isEqualTo(nyu.latitude());
    assertThat(college2MapData.name()).isEqualTo(STANDFORD_NAME);
    assertThat(college2MapData.latitude()).isEqualTo(STANFORD_LATITUDE);
  }

  @Test
  public void testException() throws IOException {
    List<CollegeId> ids = Lists.newArrayList(nyu.id());
    List<CollegeData> collegesData = Lists.newArrayList(nyu);

    when(collegeDataApi.getCollegesById(ids)).thenThrow(IOException.class);
    assertThrows(IOException.class, () -> mapsImpl.getMapsData(ids));
  }
}
