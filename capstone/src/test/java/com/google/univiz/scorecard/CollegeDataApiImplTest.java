package com.google.univiz.scorecard;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import com.google.common.io.Resources;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Provides;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.univiz.CollegeData;
import com.google.univiz.GsonModule;
import com.google.univiz.api.CollegeId;
import java.io.IOException;
import java.util.Arrays;
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
public final class CollegeDataApiImplTest {

  @Rule public final MockitoRule rule = MockitoJUnit.rule();
  @Bind @Mock private URLProvider mockUrlProvider;
  @Inject private CollegeDataApiImpl testImpl;

  static final class ReaderProviderModule extends AbstractModule {
    @Provides
    CollegeIdReaderProvider provideCollegeIdReaderProvider() {
      return new CollegeIdReaderProviderImpl();
    }
  }

  @Before
  public void setup() {
    Guice.createInjector(new GsonModule(), new ReaderProviderModule(), BoundFieldModule.of(this))
        .injectMembers(this);
  }

  @Test
  public void testGetCollegesById() throws IOException {
    CollegeId collegeId = CollegeId.create(193900);
    String scorecardUrlString =
        Resources.getResource(CollegeDataApiImplTest.class, "scorecard_response.json").toString();
    when(mockUrlProvider.getUrlFromCollegeIds(Arrays.asList(collegeId)))
        .thenReturn(scorecardUrlString);
    List<CollegeData> colleges = testImpl.getCollegesById(Arrays.asList(collegeId));
    assertThat(colleges).hasSize(1);
    CollegeData collegeData = colleges.get(0);
    assertThat(collegeData.name()).isEqualTo("New York University");
  }

  @Test
  public void testEmptyResults() throws IOException {
    CollegeId collegeId = CollegeId.create(193900);
    String scorecardUrlString =
        Resources.getResource(CollegeDataApiImplTest.class, "scorecard_response_empty.json")
            .toString();
    when(mockUrlProvider.getUrlFromCollegeIds(Arrays.asList(collegeId)))
        .thenReturn(scorecardUrlString);
    List<CollegeData> colleges = testImpl.getCollegesById(Arrays.asList(collegeId));
    assertThat(colleges).isEmpty();
  }

  @Test
  public void testMultipleResults() throws IOException {
    CollegeId collegeId = CollegeId.create(193900);
    CollegeId collegeId2 = CollegeId.create(13900);
    String scorecardUrlString =
        Resources.getResource(CollegeDataApiImplTest.class, "scorecard_response_multiple.json")
            .toString();
    when(mockUrlProvider.getUrlFromCollegeIds(Arrays.asList(collegeId)))
        .thenReturn(scorecardUrlString);
    List<CollegeData> colleges = testImpl.getCollegesById(Arrays.asList(collegeId));
    assertThat(colleges).hasSize(2);
    CollegeData collegeData = colleges.get(0);
    assertThat(collegeData.name()).isEqualTo("New York University");
    collegeData = colleges.get(1);
    assertThat(collegeData.name()).isEqualTo("New University");
  }
}
