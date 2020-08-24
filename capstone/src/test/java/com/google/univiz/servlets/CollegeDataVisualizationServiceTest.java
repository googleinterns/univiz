package com.google.univiz.servlets;

import static com.google.common.truth.Truth.*;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.CollegeStats;
import com.google.univiz.api.representation.Deadline;
import com.google.univiz.api.resource.VisResource;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(JUnit4.class)
public final class CollegeDataVisualizationServiceTest {

  private static final CollegeStats COLLEGE_STATS_1 = collegeStatsOf(1234);
  private static final CollegeStats COLLEGE_STATS_2 = collegeStatsOf(2345);

  private static final Deadline DEADLINE =
      Deadline.builder()
          .setOpeningMonth(9)
          .setOpeningDay(1)
          .setOpeningYear(2020)
          .setClosingMonth(12)
          .setClosingDay(1)
          .setClosingYear(2020)
          .build();

  @Rule public final MockitoRule rule = MockitoJUnit.rule();

  @Mock @Bind private VisResource visResource;

  @Inject private CollegeDataVisualizationService vizService;

  @Before
  public void setup() {
    Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void getStatsResults_emptyQuery() throws Exception {
    String response = doGet("/viz/" + CollegeDataVisualizationService.STATS_SUFFIX, "");

    assertThat(response).isEqualTo("[]");
  }

  @Test
  public void getStatsResults_nullQuery() throws Exception {
    String response = doGet("/viz/" + CollegeDataVisualizationService.STATS_SUFFIX, null);

    assertThat(response).isEqualTo("[]");
  }

  @Test
  public void getStatsResults_singleResult() throws Exception {
    when(visResource.getCollegeStats(ImmutableList.of(CollegeId.create(1))))
        .thenReturn(ImmutableList.of(COLLEGE_STATS_1));

    String response = doGet("/viz/" + CollegeDataVisualizationService.STATS_SUFFIX, "1");

    assertThat(response).contains("\"numOfUndergrads\":1234");
  }

  @Test
  public void getStatsResults_multiResult() throws Exception {
    when(visResource.getCollegeStats(ImmutableList.of(CollegeId.create(1), CollegeId.create(2))))
        .thenReturn(ImmutableList.of(COLLEGE_STATS_1, COLLEGE_STATS_2));

    String response = doGet("/viz/" + CollegeDataVisualizationService.STATS_SUFFIX, "1,2");

    assertThat(response).contains("\"numOfUndergrads\":1234");
    assertThat(response).contains("\"numOfUndergrads\":2345");
  }

  @Test
  public void getDeadlineResults_throws() throws Exception {
    when(visResource.getDeadlines(ImmutableList.of(CollegeId.create(1), CollegeId.create(2))))
        .thenReturn(ImmutableList.of(DEADLINE, DEADLINE));

    String response = doGet("/viz/" + CollegeDataVisualizationService.DEADLINES_SUFFIX, "1,2");

    assertThat(response).contains("\"openingMonth\":9");
    assertThat(response).contains("\"openingDay\":1");
  }

  @Test
  public void getTimelineResults_throws() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> doGet("/viz/" + CollegeDataVisualizationService.TIMELINES_SUFFIX, "1"));
  }

  private String doGet(String servletPath, String idString) throws IOException {
    HttpServletRequest mockRequest = mock(HttpServletRequest.class);
    HttpServletResponse mockResponse = mock(HttpServletResponse.class);

    when(mockRequest.getServletPath()).thenReturn(servletPath);
    when(mockRequest.getParameter("id")).thenReturn(idString);
    StringWriter stringWriter = new StringWriter();
    when(mockResponse.getWriter()).thenReturn(new PrintWriter(stringWriter));

    vizService.doGet(mockRequest, mockResponse);

    return stringWriter.toString();
  }

  private static CollegeStats collegeStatsOf(int numUndergrads) {
    return CollegeStats.builder()
        .setNumOfUndergrads(numUndergrads)
        .setAdmissionRate(1.0)
        .setAvgCost(1)
        .setAvgSat(1.0)
        .setRatioOfWomen(1.0)
        .setRatioOfMen(1.0)
        .setName("Name")
        .build();
  }
}
