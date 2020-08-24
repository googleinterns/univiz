package com.google.univiz.api.resource;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.univiz.api.CollegeDataApi;
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

  private static final CollegeData NYU_COLLEGE_DATA = MockCollegeData.getNyuData();
  private static final CollegeData STANFORD_COLLEGE_DATA = MockCollegeData.getStanfordData();

  @Before
  public void guiceSetUp() {
    Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void testMapsResourceImpl() throws IOException {
    List<CollegeId> ids = Lists.newArrayList(NYU_COLLEGE_DATA.id());
    List<CollegeData> collegesData = Lists.newArrayList(NYU_COLLEGE_DATA);

    when(collegeDataApi.getCollegesById(ids)).thenReturn(collegesData);
    List<MapsData> mapsData = mapsImpl.getMapsData(ids);
    assertThat(mapsData).hasSize(1);

    MapsData collegeMapData = mapsData.get(0);
    assertThat(collegeMapData.name()).isEqualTo(NYU_COLLEGE_DATA.name());
    assertThat(collegeMapData.latitude()).isEqualTo(NYU_COLLEGE_DATA.latitude());
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
    List<CollegeId> ids = Lists.newArrayList(NYU_COLLEGE_DATA.id(), STANFORD_COLLEGE_DATA.id());
    List<CollegeData> collegesData = Lists.newArrayList(NYU_COLLEGE_DATA, STANFORD_COLLEGE_DATA);

    when(collegeDataApi.getCollegesById(ids)).thenReturn(collegesData);
    List<MapsData> mapsData = mapsImpl.getMapsData(ids);
    assertThat(mapsData).hasSize(2);

    MapsData college1MapData = mapsData.get(0);
    MapsData college2MapData = mapsData.get(1);
    assertThat(college1MapData.name()).isEqualTo(NYU_COLLEGE_DATA.name());
    assertThat(college1MapData.latitude()).isEqualTo(NYU_COLLEGE_DATA.latitude());
    assertThat(college2MapData.name()).isEqualTo(STANFORD_COLLEGE_DATA.name());
    assertThat(college2MapData.latitude()).isEqualTo(STANFORD_COLLEGE_DATA.latitude());
  }

  @Test
  public void testException() throws IOException {
    List<CollegeId> ids = Lists.newArrayList(NYU_COLLEGE_DATA.id());
    List<CollegeData> collegesData = Lists.newArrayList(NYU_COLLEGE_DATA);

    when(collegeDataApi.getCollegesById(ids)).thenThrow(IOException.class);
    assertThrows(IOException.class, () -> mapsImpl.getMapsData(ids));
  }
}
