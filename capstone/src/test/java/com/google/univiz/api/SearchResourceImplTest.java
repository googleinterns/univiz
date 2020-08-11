package com.google.univiz.api;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class SearchResourceImplTest {
  @Mock
  SuggestionData mockSuggestionData;

  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Test
  public void testGetSuggestions() throws Exception {
  }	  
}
