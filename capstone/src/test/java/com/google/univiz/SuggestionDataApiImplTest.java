package com.google.univiz;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import com.google.common.io.Resources;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Provides;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.univiz.scorecard.SuggestionResponse;
import java.io.IOException;
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
public final class SuggestionDataApiImplTest {
  @Rule public final MockitoRule rule = MockitoJUnit.rule();
  @Bind @Mock private UrlProvider mockUrlProvider;
  @Inject private SuggestionDataApiImpl testSuggestionDataApiImpl;

  static final class ReaderProviderModule extends AbstractModule {
    @Provides
    SuggestionApiReaderProvider provideSuggestionApidReaderProvider() {
      return new SuggestionApiReaderProviderImpl();
    }
  }

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
    when(mockUrlProvider.getUrlFromCollegeName(collegeName)).thenReturn(testUrl);
    SuggestionResponse suggestionResponse =
        testSuggestionDataApiImpl.getCollegeSuggestions(collegeName);
    assertThat(suggestionResponse.suggestions()).hasSize(1);
  }
}
