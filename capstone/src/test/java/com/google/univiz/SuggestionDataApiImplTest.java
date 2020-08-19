package com.google.univiz;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.io.InputStreamReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class SuggestionDataApiImplTest {
  @Rule public final MockitoRule rule = MockitoJUnit.rule();
  @Inject private SuggestionDataApiImpl testImpl;
  
  @Before
  public void setup() {
    Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void testGetSuggestionsByEmpty() {
    InputStreamReader suggestionApiReader =
        new InputStreamReader(
            Resources.getResource(SuggestionDataApiImplTest.class, "suggestion_api_impl.json")
                .openStream());
   

  }
}
