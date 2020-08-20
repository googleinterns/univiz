package com.google.univiz.api;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.SearchData;
import com.google.univiz.api.representation.SuggestionData;
import com.google.univiz.api.representation.SuggestionResponse;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(JUnit4.class)
public final class SearchResourceImplTest {
  @Mock private SuggestionDataApi mockSuggestionApi;
  @Mock private SuggestionResponse cannedResponse;
  @Mock private SuggestionData cannedSuggestion;
  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  private SearchResourceImpl search;

  @Before
  public void setup() {
    search = new SearchResourceImpl(mockSuggestionApi);
  }

  @Test
  public void testSuggestionsForCollegeNameNoSpace() throws Exception {
    String collegeName = "Stanford";
    SearchResourceImpl search = new SearchResourceImpl(mockSuggestionApi);
    List<SuggestionData> suggestions = Lists.newArrayList(cannedSuggestion);
    when(cannedResponse.suggestions()).thenReturn(suggestions);
    when(cannedSuggestion.name()).thenReturn("Stanford University");
    when(cannedSuggestion.id()).thenReturn(1);
    when(mockSuggestionApi.getCollegeSuggestions(collegeName)).thenReturn(cannedResponse);

    List<SearchData> ret = search.getSearchSuggestions(collegeName);
    CollegeId collegeId = CollegeId.create(1);
    SearchData expected = SearchData.create("Stanford University", collegeId);
    assertThat(ret).containsExactly(expected);
  }

  @Test
  public void testSuggestionsForEmptySuggestion() throws Exception {
    String emptyCollegeName = "";
    List<SearchData> ret = search.getSearchSuggestions(emptyCollegeName);
    CollegeId collegeId = CollegeId.create(0);
    SearchData expected = SearchData.create("", collegeId);
    assertThat(ret).containsExactly(expected);
  }
  
  @Test
  public void testSuggestionsForNullSuggestion() throws Exception {
    String nullCollegeName = null;
    List<SearchData> ret = search.getSearchSuggestions(nullCollegeName);
    CollegeId collegeId = CollegeId.create(0);
    SearchData expected = SearchData.create("", collegeId);
    assertThat(ret).containsExactly(expected);
  }

  @Test
  public void testSuggestionsForInvalidSuggestion() throws Exception {
    String invalidCollegeName = "%$#@!1235566";
    List<SearchData> ret = search.getSearchSuggestions(invalidCollegeName);
    CollegeId collegeId = CollegeId.create(0);
    SearchData expected = SearchData.create("", collegeId);
    assertThat(ret).containsExactly(expected);
  }
}
