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
import com.google.univiz.api.representation.MockCollegeData;
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
  private final CollegeData stanford = MockCollegeData.getStanfordData();

  @Before
  public void guiceSetUp() {
    Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void testMapsResourceImpl() throws IOException {
    List<CollegeId> ids = Lists.newArrayList(nyu.id());

    List<CollegeData> collegesData = Lists.newArrayList(nyu);

    when(collegeDataApi.getCollegesById(ids)).thenReturn(collegesData);
    assertThat(collegesData).hasSize(1);
    List<MapsData> mapsData = mapsImpl.getMapsData(ids);
    assertThat(mapsData).hasSize(1);

    MapsData collegeMapData = mapsData.get(0);
    assertThat(collegeMapData.name()).isEqualTo("New York University");
    assertThat(collegeMapData.latitude()).isEqualTo(40.729452);
  }

  @Test
  public void testEmptyMapsResourcesImpl() throws IOException {
    List<CollegeId> ids = new ArrayList<>();
    List<CollegeData> collegesData = new ArrayList<>();

    when(collegeDataApi.getCollegesById(ids)).thenReturn(collegesData);
    assertThat(collegesData).isEmpty();
    List<MapsData> mapsData = mapsImpl.getMapsData(ids);
    assertThat(mapsData).isEmpty();
  }

  @Test
  public void testManyMapsResourcesImpl() throws IOException {
    List<CollegeId> ids = Lists.newArrayList(nyu.id(), stanford.id());

    List<CollegeData> collegesData = Lists.newArrayList(nyu, stanford);

    when(collegeDataApi.getCollegesById(ids)).thenReturn(collegesData);
    List<MapsData> mapsData = mapsImpl.getMapsData(ids);
    assertThat(mapsData).hasSize(2);

    MapsData college1MapData = mapsData.get(0);
    MapsData college2MapData = mapsData.get(1);
    assertThat(college1MapData.name()).isEqualTo("New York University");
    assertThat(college1MapData.latitude()).isEqualTo(40.729452);
    assertThat(college2MapData.name()).isEqualTo("Stanford University");
    assertThat(college2MapData.latitude()).isEqualTo(37.429434);
  }
}
