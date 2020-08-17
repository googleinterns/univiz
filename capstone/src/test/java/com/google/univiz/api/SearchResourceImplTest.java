package com.google.univiz.api;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(JUnit4.class)
public final class SearchResourceImplTest {
  private SearchResourceImpl search;
  @Mock private SuggestionDataApi mockSuggestionApi;

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  
  @Before
  public void setup() {
    search = new SearchResourceImpl(mockSuggestionApi);
  }
  
  @Test
  public void testGetSuggestions() throws Exception {
    String partialCollegeName = "Sta";
    SuggestionData stanford = new SuggestionData("Stanford University", 0);
    List<SuggestionData> cannedResponse = List.newArrayList(stanford);
    when(mockSuggestionApi.getCollegeSuggestions(partialCollegeName)).thenReturn(cannedResponse);

    List<SearchData> ret = search.getSearchSuggestions(partialCollegeName);
    SearchData expected = SearchData.create("Stanford University", 0);
    assertThat(ret).containsExactly(expected);
  }
}
