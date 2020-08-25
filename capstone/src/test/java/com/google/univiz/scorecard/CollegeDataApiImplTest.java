package com.google.univiz.scorecard;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import com.google.common.io.Resources;
import com.google.inject.Guice;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.univiz.GsonModule;
import com.google.univiz.api.representation.CollegeData;
import com.google.univiz.api.representation.CollegeId;
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
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(JUnit4.class)
public final class CollegeDataApiImplTest {

  @Rule public final MockitoRule rule = MockitoJUnit.rule();
  @Bind @Mock private URLProvider mockUrlProvider;
  @Bind @Spy private CollegeIdReaderProvider readerProvider = new CollegeIdReaderProviderImpl();
  @Inject private CollegeDataApiImpl testImpl;

  @Before
  public void setup() {
    Guice.createInjector(new GsonModule(), BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void testGetCollegesById() throws IOException {
    CollegeId collegeId = CollegeId.create(193900);
    String scorecardUrlString =
        Resources.getResource(CollegeDataApiImplTest.class, "scorecard_response.json").toString();
    when(mockUrlProvider.getDataUrl(Arrays.asList(collegeId))).thenReturn(scorecardUrlString);
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
    when(mockUrlProvider.getDataUrl(Arrays.asList(collegeId))).thenReturn(scorecardUrlString);
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
    when(mockUrlProvider.getDataUrl(Arrays.asList(collegeId, collegeId2)))
        .thenReturn(scorecardUrlString);
    List<CollegeData> colleges = testImpl.getCollegesById(Arrays.asList(collegeId, collegeId2));
    assertThat(colleges).hasSize(2);
    CollegeData collegeData = colleges.get(0);
    assertThat(collegeData.name()).isEqualTo("New York University");
    collegeData = colleges.get(1);
    assertThat(collegeData.name()).isEqualTo("New University");
  }

  @Test
  public void testIOException() throws IOException {
    CollegeId collegeId = CollegeId.create(193900);
    String scorecardUrlString =
        Resources.getResource(CollegeDataApiImplTest.class, "scorecard_response_empty.json")
            .toString();
    when(mockUrlProvider.getDataUrl(Arrays.asList(collegeId))).thenReturn(scorecardUrlString);
    when(readerProvider.getStreamFromUrl(scorecardUrlString)).thenThrow(new IOException());
    assertThrows(IOException.class, () -> testImpl.getCollegesById(Arrays.asList(collegeId)));
  }
}
