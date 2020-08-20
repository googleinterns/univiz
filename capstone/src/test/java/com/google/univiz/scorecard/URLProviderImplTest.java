package com.google.univiz.scorecard;

import static com.google.common.truth.Truth.assertThat;
import static java.util.stream.Collectors.joining;

import com.google.inject.Guice;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.config.UnivizConfigModule;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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

    Set<String> expectedFields = new LinkedHashSet<>();
    expectedFields.add("id");
    expectedFields.add("school.name");
    expectedFields.add("school.city");
    expectedFields.add("school.main_campus");
    expectedFields.add("location.lat");
    expectedFields.add("location.lon");
    expectedFields.add("school.carnegie_size_setting");
    expectedFields.add("latest.admissions.admission_rate.overall");
    expectedFields.add("latest.admissions.sat_scores.average.overall");
    expectedFields.add("latest.student.size");
    expectedFields.add("latest.cost.attendance.academic_year");
    expectedFields.add("latest.student.demographics.men");
    expectedFields.add("latest.student.demographics.women");

    StringBuilder expectedBuilder =
        new StringBuilder("https://api.data.gov/ed/collegescorecard/v1/schools.json?id=");
    expectedBuilder.append(
        String.format("%d,%d,%d", fakeCollegeId1.id(), fakeCollegeId2.id(), fakeCollegeId3.id()));
    expectedBuilder.append("&per_page=3&fields=");
    expectedBuilder.append(expectedFields.stream().sorted().collect(joining(",")));
    expectedBuilder.append("&api_key=scorecard_test_key");
    String expected = expectedBuilder.toString();

    assertThat(actual).isEqualTo(expected);
  }
}
