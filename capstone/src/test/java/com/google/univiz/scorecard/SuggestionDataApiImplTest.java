package com.google.univiz.scorecard;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import com.google.common.io.Resources;
import com.google.inject.Guice;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.univiz.GsonModule;
import com.google.univiz.api.representation.SuggestionData;
import com.google.univiz.api.representation.SuggestionResponse;
import com.google.univiz.scorecard.CollegeIdReaderProvider;
import com.google.univiz.scorecard.CollegeIdReaderProviderImpl;
import com.google.univiz.scorecard.URLProvider;
import java.io.IOException;
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
public final class SuggestionDataApiImplTest {
  @Rule public final MockitoRule rule = MockitoJUnit.rule();
  @Bind @Mock private URLProvider mockUrlProvider;
  @Bind @Spy private CollegeIdReaderProvider readerProvider = new CollegeIdReaderProviderImpl();
  @Inject private SuggestionDataApiImpl testSuggestionDataApiImpl;

  @Before
  public void setup() {
    Guice.createInjector(new GsonModule(), BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void testEmptySuggestion() throws IOException {
    String collegeName = "";
    String testUrl =
        Resources.getResource(SuggestionDataApiImplTest.class, "suggestion_empty.json").toString();
    when(mockUrlProvider.getSuggestionUrl(collegeName)).thenReturn(testUrl);
    SuggestionResponse suggestionResponse =
        testSuggestionDataApiImpl.getCollegeSuggestions(collegeName);
    assertThat(suggestionResponse.suggestions()).isEmpty();
  }

  @Test
  public void testSingleSuggestion() throws IOException {
    String collegeName = "Stanf";
    String testUrl =
        Resources.getResource(SuggestionDataApiImplTest.class, "suggestion_single.json").toString();
    when(mockUrlProvider.getSuggestionUrl(collegeName)).thenReturn(testUrl);
    SuggestionResponse suggestionResponse =
        testSuggestionDataApiImpl.getCollegeSuggestions(collegeName);
    assertThat(suggestionResponse.suggestions()).hasSize(1);
    SuggestionData suggData = suggestionResponse.suggestions().get(0);
    assertThat(suggData.name()).isEqualTo("Stanford University");
  }

  @Test
  public void testMultipleSuggestions() throws IOException {
    String collegeName = "Stan";
    String testUrl =
        Resources.getResource(SuggestionDataApiImplTest.class, "suggestion_multiple.json")
            .toString();
    when(mockUrlProvider.getSuggestionUrl(collegeName)).thenReturn(testUrl);
    SuggestionResponse suggestionResponse =
        testSuggestionDataApiImpl.getCollegeSuggestions(collegeName);
    assertThat(suggestionResponse.suggestions()).hasSize(2);
    SuggestionData suggDataOne = suggestionResponse.suggestions().get(0);
    assertThat(suggDataOne.name()).isEqualTo("Stanford University");
    SuggestionData suggDataTwo = suggestionResponse.suggestions().get(1);
    assertThat(suggDataTwo.name()).isEqualTo("Standford University");
  }

  @Test
  public void testIOException() throws IOException {
    String collegeName = "";
    String testUrl =
        Resources.getResource(SuggestionDataApiImplTest.class, "suggestion_multiple.json")
            .toString();
    when(mockUrlProvider.getSuggestionUrl(collegeName)).thenReturn(testUrl);
    when(readerProvider.getStreamFromUrl(testUrl)).thenThrow(new IOException());
    assertThrows(
        IOException.class, () -> testSuggestionDataApiImpl.getCollegeSuggestions(collegeName));
  }
}
