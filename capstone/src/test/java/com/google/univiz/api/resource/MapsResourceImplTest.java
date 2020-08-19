package com.google.univiz.api.resource;

import static com.google.common.truth.Truth.assertThat;
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

  private static final int COLLEGE_ID = 193900;
  private static final String NAME = "New York University";
  private static final String CITY = "New York";
  private static final boolean FLAG_MAIN_CAMPUS = true;
  private static final double LATITUDE = 40.729452;
  private static final double LONGITUDE = -73.997264;
  private static final int CARNEGIE_SIZE_DEGREE = 17;
  private static final double ADMISSION_RATE = 0.1999;
  private static final double AVG_SAT = 1419.0;
  private static final int NUM_OF_UNDERGRADS = 26339;
  private static final int AVG_COST = 69830;
  private static final double RATIO_OF_MEN = 0.4253;
  private static final double RATIO_OF_WOMEN = 0.5747;

  @Before
  public void guiceSetUp() {
    Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void testMapsResourceImpl() {
    CollegeId id = CollegeId.create(COLLEGE_ID);
    List<CollegeId> ids = Lists.newArrayList(id);
    assertThat(ids).hasSize(1);

    CollegeData college =
        CollegeData.builder()
            .setId(id)
            .setName(NAME)
            .setCity(CITY)
            .setIsMainCampus(FLAG_MAIN_CAMPUS)
            .setLatitude(LATITUDE)
            .setLongitude(LONGITUDE)
            .setCarnegieSizeDegree(CarnegieSizeDegree.getDegree(CARNEGIE_SIZE_DEGREE))
            .setAdmissionRate(ADMISSION_RATE)
            .setAvgSat(AVG_SAT)
            .setNumOfUndergrads(NUM_OF_UNDERGRADS)
            .setAvgCost(AVG_COST)
            .setRatioOfMen(RATIO_OF_MEN)
            .setRatioOfWomen(RATIO_OF_WOMEN)
            .build();
    List<CollegeData> collegesData = Lists.newArrayList(college);
    assertThat(collegesData).hasSize(1);

    when(collegeDataApi.getCollegesById(ids)).thenReturn(collegesData);
    List<MapsData> mapsData = mapsImpl.getMapsData(ids);

    MapsData collegeMapData = mapsData.get(0);
    assertThat(collegeMapData.name()).isEqualTo("New York University");
    assertThat(collegeMapData.latitude()).isEqualTo(40.729452);
  }

  @Test
  public void testEmptyMapsResourcesImpl() {
    List<CollegeId> ids = new ArrayList<>();
    assertThat(ids).hasSize(0);

    List<CollegeData> collegesData = new ArrayList<>();
    assertThat(collegesData).hasSize(0);

    when(collegeDataApi.getCollegesById(ids)).thenReturn(collegesData);
    List<MapsData> mapsData = mapsImpl.getMapsData(ids);
    assertThat(mapsData).hasSize(0);
  }

  @Test
  public void testManyMapsResourcesImpl() {
    CollegeId id1 = CollegeId.create(COLLEGE_ID);
    CollegeId id2 = CollegeId.create(243744);
    List<CollegeId> ids = Lists.newArrayList(id1, id2);
    assertThat(ids).hasSize(2);

    CollegeData college1 =
        CollegeData.builder()
            .setId(id1)
            .setName(NAME)
            .setCity(CITY)
            .setIsMainCampus(FLAG_MAIN_CAMPUS)
            .setLatitude(LATITUDE)
            .setLongitude(LONGITUDE)
            .setCarnegieSizeDegree(CarnegieSizeDegree.getDegree(CARNEGIE_SIZE_DEGREE))
            .setAdmissionRate(ADMISSION_RATE)
            .setAvgSat(AVG_SAT)
            .setNumOfUndergrads(NUM_OF_UNDERGRADS)
            .setAvgCost(AVG_COST)
            .setRatioOfMen(RATIO_OF_MEN)
            .setRatioOfWomen(RATIO_OF_WOMEN)
            .build();
    CollegeData college2 =
        CollegeData.builder()
            .setId(id2)
            .setName("Stanford University")
            .setCity("Stanford")
            .setIsMainCampus(true)
            .setLatitude(37.429434)
            .setLongitude(-122.167359)
            .setCarnegieSizeDegree(CarnegieSizeDegree.getDegree(CARNEGIE_SIZE_DEGREE))
            .setAdmissionRate(0.0436)
            .setAvgSat(1497.0)
            .setNumOfUndergrads(7083)
            .setAvgCost(69109)
            .setRatioOfMen(0.4991)
            .setRatioOfWomen(0.5009)
            .build();

    List<CollegeData> collegesData = Lists.newArrayList(college1, college2);
    assertThat(collegesData).hasSize(2);

    when(collegeDataApi.getCollegesById(ids)).thenReturn(collegesData);
    List<MapsData> mapsData = mapsImpl.getMapsData(ids);

    MapsData college1MapData = mapsData.get(0);
    MapsData college2MapData = mapsData.get(1);
    assertThat(college1MapData.name()).isEqualTo("New York University");
    assertThat(college1MapData.latitude()).isEqualTo(40.729452);
    assertThat(college2MapData.name()).isEqualTo("Stanford University");
    assertThat(college2MapData.latitude()).isEqualTo(37.429434);
  }
}
