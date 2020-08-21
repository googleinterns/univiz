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
import com.google.univiz.api.representation.CollegeStats;
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
  private static final String NYU_NAME = "New York University";
  private static final String NYU_CITY = "New York";
  private static final boolean NYU_FLAG_MAIN_CAMPUS = true;
  private static final double NYU_LATITUDE = 40.729452;
  private static final double NYU_LONGITUDE = -73.997264;
  private static final int CARNEGIE_SIZE_DEGREE = 17;
  private static final double NYU_ADMISSION_RATE = 0.1999;
  private static final double NYU_AVG_SAT = 1419.0;
  private static final int NYU_NUM_OF_UNDERGRADS = 26339;
  private static final int NYU_AVG_COST = 69830;
  private static final double NYU_RATIO_OF_MEN = 0.4253;
  private static final double NYU_RATIO_OF_WOMEN = 0.5747;

  private static final CollegeId STANFORD_COLLEGE_ID = CollegeId.create(243744);
  private static final String STANDFORD_NAME = "Stanford University";
  private static final String STANDFORD_CITY = "Stanford";
  private static final boolean STANFORD_FLAG_MAIN_CAMPUS = true;
  private static final double STANFORD_LATITUDE = 37.429434;
  private static final double STANFORD_LONGITUDE = -122.167359;
  private static final double STANFORD_ADMISSION_RATE = 0.0436;
  private static final double STANFORD_AVG_SAT = 1497.0;
  private static final int STANFORD_NUM_OF_UNDERGRADS = 7083;
  private static final int STANFORD_AVG_COST = 69109;
  private static final double STANFORD_RATIO_OF_MEN = 0.4991;
  private static final double STANFORD_RATIO_OF_WOMEN = 0.5009;

  private static final CollegeData NYU_COLLEGE_DATA =
      CollegeData.builder()
          .setId(NYU_COLLEGE_ID)
          .setName(NYU_NAME)
          .setCity(NYU_CITY)
          .setIsMainCampus(NYU_FLAG_MAIN_CAMPUS)
          .setLatitude(NYU_LATITUDE)
          .setLongitude(NYU_LONGITUDE)
          .setCarnegieSizeDegree(CarnegieSizeDegree.getDegree(CARNEGIE_SIZE_DEGREE))
          .setAdmissionRate(NYU_ADMISSION_RATE)
          .setAvgSat(NYU_AVG_SAT)
          .setNumOfUndergrads(NYU_NUM_OF_UNDERGRADS)
          .setAvgCost(NYU_AVG_COST)
          .setRatioOfMen(NYU_RATIO_OF_MEN)
          .setRatioOfWomen(NYU_RATIO_OF_WOMEN)
          .build();

  private static final CollegeData STANFORD_COLLEGE_DATA =
      CollegeData.builder()
          .setId(STANFORD_COLLEGE_ID)
          .setName(STANDFORD_NAME)
          .setCity(STANDFORD_CITY)
          .setIsMainCampus(STANFORD_FLAG_MAIN_CAMPUS)
          .setLatitude(STANFORD_LATITUDE)
          .setLongitude(STANFORD_LONGITUDE)
          .setCarnegieSizeDegree(CarnegieSizeDegree.getDegree(CARNEGIE_SIZE_DEGREE))
          .setAdmissionRate(STANFORD_ADMISSION_RATE)
          .setAvgSat(STANFORD_AVG_SAT)
          .setNumOfUndergrads(STANFORD_NUM_OF_UNDERGRADS)
          .setAvgCost(STANFORD_AVG_COST)
          .setRatioOfMen(STANFORD_RATIO_OF_MEN)
          .setRatioOfWomen(STANFORD_RATIO_OF_WOMEN)
          .build();

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
