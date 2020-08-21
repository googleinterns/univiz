package com.google.univiz.api;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import com.google.common.io.Resources;
import com.google.inject.Guice;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.univiz.api.representation.SuggestionResponse;
import com.google.univiz.scorecard.CollegeIdReaderProvider;
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
  @Bind @Spy private CollegeIdReaderProvider readerProvider = new CollegeIdReaderProvider();
  @Inject private SuggestionDataApiImpl testSuggestionDataApiImpl;

  @Before
  public void setup() {
    Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void testGetSuggestionsByEmpty() throws IOException {
    String collegeName = "";
    String testUrl =
        Resources.getResource(SuggestionDataApiImplTest.class, "suggestion_api_impl.json")
            .toString();
    when(mockUrlProvider.getUrl(collegeName)).thenReturn(testUrl);
    SuggestionResponse suggestionResponse =
        testSuggestionDataApiImpl.getCollegeSuggestions(collegeName);
    assertThat(suggestionResponse.suggestions()).hasSize(1);
  }
}
