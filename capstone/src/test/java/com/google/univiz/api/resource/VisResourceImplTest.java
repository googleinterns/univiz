package com.google.univiz.api.resource;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.univiz.api.CollegeDataApi;
import com.google.univiz.api.representation.CollegeData;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.CollegeStats;
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
public final class VisResourceImplTest {

  @Rule public final MockitoRule rule = MockitoJUnit.rule();

  @Bind @Mock private CollegeDataApi collegeDataApi;

  @Inject private VisResourceImpl visImpl;

  private static final CollegeId NYU_COLLEGE_ID = CollegeId.create(193900);
  private static final CollegeData NYU_COLLEGE_DATA = MockCollegeData.getNyuData();

  private static final CollegeId STANFORD_COLLEGE_ID = CollegeId.create(243744);
  private static final CollegeData STANFORD_COLLEGE_DATA = MockCollegeData.getStanfordData();

  @Before
  public void guiceSetUp() {
    Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void testVisResourceImpl() throws IOException {
    List<CollegeId> ids = Lists.newArrayList(NYU_COLLEGE_ID);

    List<CollegeData> collegesData = Lists.newArrayList(NYU_COLLEGE_DATA);

    when(collegeDataApi.getCollegesById(ids)).thenReturn(collegesData);
    List<CollegeStats> visData = visImpl.getCollegeStats(ids);
    assertThat(visData).hasSize(1);

    CollegeStats collegeStats = visData.get(0);
    assertThat(collegeStats.name()).isEqualTo("New York University");
    assertThat(collegeStats.avgCost()).isEqualTo(69830);
  }

  @Test
  public void testEmptyMapsResourcesImpl() throws IOException {
    List<CollegeId> ids = new ArrayList<>();
    List<CollegeData> collegesData = new ArrayList<>();

    when(collegeDataApi.getCollegesById(ids)).thenReturn(collegesData);
    List<CollegeStats> visData = visImpl.getCollegeStats(ids);
    assertThat(visData).isEmpty();
  }

  @Test
  public void testManyVisResourcesImpl() throws IOException {
    List<CollegeId> ids = Lists.newArrayList(NYU_COLLEGE_ID, STANFORD_COLLEGE_ID);

    List<CollegeData> collegesData = Lists.newArrayList(NYU_COLLEGE_DATA, STANFORD_COLLEGE_DATA);

    when(collegeDataApi.getCollegesById(ids)).thenReturn(collegesData);
    List<CollegeStats> visData = visImpl.getCollegeStats(ids);
    assertThat(visData).hasSize(2);

    CollegeStats college1Stats = visData.get(0);
    CollegeStats college2Stats = visData.get(1);
    assertThat(college1Stats.name()).isEqualTo("New York University");
    assertThat(college1Stats.avgCost()).isEqualTo(69830);
    assertThat(college2Stats.name()).isEqualTo("Stanford University");
    assertThat(college2Stats.avgCost()).isEqualTo(69109);
  }
}
