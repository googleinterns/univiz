package com.google.univiz.scorecard;

import static com.google.common.truth.Truth.assertThat;

import com.google.inject.Guice;
import com.google.univiz.api.CollegeId;
import com.google.univiz.config.UnivizConfigModule;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(JUnit4.class)
public final class URLProviderImplTest {
  @Rule public final MockitoRule rule = MockitoJUnit.rule();
  @Inject private URLProviderImpl testImpl;

  @Before
  public void setup() {
    Guice.createInjector(new UnivizConfigModule()).injectMembers(this);
  }

  @Test
  public void testGetUrlFromCollegeIds() {
    CollegeId fakeCollegeId1 = CollegeId.create(1);
    CollegeId fakeCollegeId2 = CollegeId.create(2);
    CollegeId fakeCollegeId3 = CollegeId.create(3);
    List<CollegeId> ids = Arrays.asList(fakeCollegeId1, fakeCollegeId2, fakeCollegeId3);
    String actual = testImpl.getUrlFromCollegeIds(ids);
    StringBuilder expectedBuilder =
        new StringBuilder("https://api.data.gov/ed/collegescorecard/v1/schools.json?id=");
    expectedBuilder.append(
        String.format("%d,%d,%d", fakeCollegeId1.id(), fakeCollegeId2.id(), fakeCollegeId3.id()));
    expectedBuilder.append("&per_page=1&fields=");
    expectedBuilder.append("id,school.name,school.city,school.main_campus,");
    expectedBuilder.append(
        "location.lat,location.lon,school.carnegie_size_setting,latest.admissions.admission_rate.overall,");
    expectedBuilder.append("latest.admissions.sat_scores.average.overall,latest.student.size,");
    expectedBuilder.append("latest.cost.attendance.academic_year,latest.student.demographics.men,");
    expectedBuilder.append("latest.student.demographics.women&api_key=");
    expectedBuilder.append("scorecard_test_key");
    String expected = expectedBuilder.toString();

    assertThat(actual).isEqualTo(expected);
  }
}
