package com.google.univiz.api;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import com.google.univiz.SuggestionDataApi;
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
  @Mock private SuggestionResponse cannedResponse;
  @Mock private SuggestionData cannedSuggestion;
  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();

  @Before
  public void setup() {
    search = new SearchResourceImpl(mockSuggestionApi);
  }

  @Test
  public void testSuggestionsForFullCollegeName() throws Exception {
    String fullCollegeName = "Stanford University";
    List<SuggestionData> suggestions = newArrayList(cannedSuggestion);
    when(cannedResponse.suggestions()).thenReturn(suggestions);
    when(cannedSuggestion.name()).thenReturn("Stanford University");
    when(cannedSuggestion.id()).thenReturn(1);
    when(mockSuggestionApi.getCollegeSuggestions(emptyCollegeName)).thenReturn(cannedResponse);

    List<SearchData> ret = search.getSearchSuggestions(fullCollegeName);
    CollegeId collegeId = CollegeId.create(1);
    SearchData expected = SearchData.create("Stanford University", collegeId);
    assertThat(ret).containsExactly(expected);
  }

  @Test
  public void testSuggestionsForEmptySuggestion() throws Exception {
    String emptyCollegeName = "";
    List<SuggestionData> suggestions = newArrayList(cannedSuggestion);
    when(cannedResponse.suggestions()).thenReturn(suggestions);
    when(cannedSuggestion.name()).thenReturn("");
    when(cannedSuggestion.id()).thenReturn(0);
    when(mockSuggestionApi.getCollegeSuggestions(emptyCollegeName)).thenReturn(cannedResponse);

    List<SearchData> ret = search.getSearchSuggestions(emptyCollegeName);
    CollegeId collegeId = CollegeId.create(0);
    SearchData expected = SearchData.create("", collegeId);
    assertThat(ret).containsExactly(expected);
  }

  @Test
  public void testSuggestionsForNullSuggestion() throws Exception {
    String nullCollegeName = null;
    List<SuggestionData> suggestions = newArrayList(cannedSuggestion);
    when(cannedResponse.suggestions()).thenReturn(suggestions);
    when(cannedSuggestion.name()).thenReturn("");
    when(cannedSuggestion.id()).thenReturn(0);
    when(mockSuggestionApi.getCollegeSuggestions(nullCollegeName)).thenReturn(cannedResponse);

    List<SearchData> ret = search.getSearchSuggestions(nullCollegeName);
    CollegeId collegeId = CollegeId.create(0);
    SearchData expected = SearchData.create("", collegeId);
    assertThat(ret).containsExactly(expected);
  }

  @Test
  public void testSuggestionsForInvalidSuggestion() throws Exception {
    String invalidCollegeName = "%$#@!1235566";
    List<SuggestionData> suggestions = newArrayList(cannedSuggestion);
    when(cannedResponse.suggestions()).thenReturn(suggestions);
    when(cannedSuggestion.name()).thenReturn("");
    when(cannedSuggestion.id()).thenReturn(0);
    when(mockSuggestionApi.getCollegeSuggestions(nullCollegeName)).thenReturn(cannedResponse);

    List<SearchData> ret = search.getSearchSuggestions(invalidCollegeName);
    CollegeId collegeId = CollegeId.create(0);
    SearchData expected = SearchData.create("", collegeId);
    assertThat(ret).containsExactly(expected);
  }

  /*TODO:  @Test
  public void testMultipleSuggestionsForPartialCollegeName() throws Exception {
  }

  @Test
  public void testNoSuggestionsForValidPartialName() throws Exception {
  }*/
}
