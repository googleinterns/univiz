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
  private static final String FRONT_URL =
      "https://api.data.gov/ed/collegescorecard/v1/schools.json?";
  private static final String QUERY_SCHOOL_NAME = "school.name=";
  private static final String QUERY_TYPE_ID = "id=";
  private static final String PER_PAGE = "&per_page=";
  private static final String PAGE_NUMBER = "&page=";
  private static final String FIELDS_PARAM = "&fields=";
  private static final String SUGGESTION_FIELDS = "id,school.name";
  private static final String ID_FIELD = "id";
  private static final String SCHOOL_NAME = "school.name";
  private static final String SCHOOL_CITY = "school.city";
  private static final String SCHOOL_MAIN_CAMPUS = "school.main_campus";
  private static final String LOCATION_LAT = "location.lat";
  private static final String LOCATION_LON = "location.lon";
  private static final String CARNEGIE_SIZE_SETTING = "school.carnegie_size_setting";
  private static final String ADMISSION_RATE = "latest.admissions.admission_rate.overall";
  private static final String SAT_SCORES = "latest.admissions.sat_scores.average.overall";
  private static final String SIZE = "latest.student.size";
  private static final String ACADEMIC_YEAR = "latest.cost.attendance.academic_year";
  private static final String MEN = "latest.student.demographics.men";
  private static final String WOMEN = "latest.student.demographics.women";
  private static final String API_KEY = "&api_key=scorecard_test_key";
  @Rule public final MockitoRule rule = MockitoJUnit.rule();
  @Inject private URLProviderImpl testImpl;

  @Before
  public void setup() {
    Guice.createInjector(new UnivizConfigModule()).injectMembers(this);
  }

  @Test
  public void testGetUrlFromPartialCollegeName() {
    String partialCollegeName = "Sta";
    String actual = testImpl.getSuggestionUrl(partialCollegeName, "0");

    StringBuilder expectedBuilder = new StringBuilder();
    expectedBuilder.append(FRONT_URL);
    expectedBuilder.append(QUERY_SCHOOL_NAME);
    expectedBuilder.append(partialCollegeName);
    expectedBuilder.append(FIELDS_PARAM);
    expectedBuilder.append(SUGGESTION_FIELDS);
    expectedBuilder.append(PER_PAGE);
    expectedBuilder.append("100");
    expectedBuilder.append(PAGE_NUMBER);
    expectedBuilder.append("0");
    expectedBuilder.append(API_KEY);
    String expected = expectedBuilder.toString();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testGetUrlFromCollegeIds() {
    CollegeId fakeCollegeId1 = CollegeId.create(1);
    CollegeId fakeCollegeId2 = CollegeId.create(2);
    CollegeId fakeCollegeId3 = CollegeId.create(3);
    List<CollegeId> ids = Arrays.asList(fakeCollegeId1, fakeCollegeId2, fakeCollegeId3);
    String actual = testImpl.getDataUrl(ids);

    Set<String> expectedFields = new LinkedHashSet<>();
    expectedFields.add(ID_FIELD);
    expectedFields.add(SCHOOL_NAME);
    expectedFields.add(SCHOOL_CITY);
    expectedFields.add(SCHOOL_MAIN_CAMPUS);
    expectedFields.add(LOCATION_LAT);
    expectedFields.add(LOCATION_LON);
    expectedFields.add(CARNEGIE_SIZE_SETTING);
    expectedFields.add(ADMISSION_RATE);
    expectedFields.add(SAT_SCORES);
    expectedFields.add(SIZE);
    expectedFields.add(ACADEMIC_YEAR);
    expectedFields.add(MEN);
    expectedFields.add(WOMEN);

    StringBuilder expectedBuilder = new StringBuilder(FRONT_URL);
    expectedBuilder.append(QUERY_TYPE_ID);
    expectedBuilder.append(
        String.format("%d,%d,%d", fakeCollegeId1.id(), fakeCollegeId2.id(), fakeCollegeId3.id()));
    expectedBuilder.append(PER_PAGE);
    expectedBuilder.append("3");
    expectedBuilder.append(FIELDS_PARAM);
    expectedBuilder.append(expectedFields.stream().sorted().collect(joining(",")));
    expectedBuilder.append(API_KEY);
    String expected = expectedBuilder.toString();

    assertThat(actual).isEqualTo(expected);
  }
}
