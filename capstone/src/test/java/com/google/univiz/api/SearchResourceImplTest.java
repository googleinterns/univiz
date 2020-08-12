package com.google.univiz.api;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class SearchResourceImplTest {
  @Mock SuggestionData mockSuggestionData;

  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Test
  public void testGetSuggestions() throws Exception {
    String partialCollegeName = "Sta";
    List<SearchData> ret = getSearchSuggestions(partialCollegeName);
    SearchData expected = create("Stanford University", 0);
    assertThat(expected.getCollegeName()).isEqualTo(ret.get(0).getCollegeName());
    assertThat(expected.getCollegeId()).isEqualTo(ret.get(0).getCollegeId());
  }
}
