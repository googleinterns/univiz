package com.google.univiz.scorecard;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.testing.BoundFieldModule;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(JUnit4.class)
public final class CollegeDataApiImplTest {

  @Rule public MockitoRule rule = MockitoJUnit.rule();
  @Bind @Mock CollegeIdReaderProvider mockReaderProvider;
  @Bind ScorecardConverter scorecardConverter = new ScorecardConverter();
  @Bind UnivizConfig config;
  @Inject CollegeDataApiImpl testImpl;
  
  @Before
  public void setup() {
    Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void testGetCollegesById() throws IOException {
    Gson gson = GsonBuilder().registerTypeAdapterFactoru(GenerateTypeAdapter.FACTORY).create();
    InputStreamReader scorecardReader = new InputStreamReader(Resources.getResource(CollegeDataApiImplTest.class, "scorecard_response.json").openStream());
    CollegeId collegeId = CollegeId.create(193900);
    when(mockReaderProvider.getReaderFromCollegeId(collegeId)).thenReturn(scorecardReader);
    List<CollegeData> colleges = testImpl.getCollegesById();
    assertThat(colleges).hasSize(1);
    CollegeData collegeData = colleges.get(0);
    assertThat(college.name()).isEqualTo("New York University");
  }
}
