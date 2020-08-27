package com.google.univiz.api.resource;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.SearchData;
import com.google.univiz.api.representation.SuggestionData;
import com.google.univiz.api.representation.SuggestionResponse;
import com.google.univiz.scorecard.SuggestionDataApi;
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
  private static final SuggestionData STANFORD_SUGGESTION_DATA =
      SuggestionData.create("Stanford University", 1);
  private static final SuggestionData CAL_STATE_SUGGESTION_DATA = SuggestionData.create("California State University Stanislaus", 2);
  private static final SuggestionResponse STANFORD_SUGGESTION_RESPONSE =
      SuggestionResponse.create(Lists.newArrayList(STANFORD_SUGGESTION_DATA));
  private static final SuggestionResponse MULTIPLE_SUGGESTION_RESPONSE = SuggestionResponse.create(Lists.newArrayList(STANFORD_SUGGESTION_DATA, CAL_STATE_SUGGESTION_DATA));
  private static final SuggestionData NULL_SUGGESTION_DATA = SuggestionData.create(null, 1);
  private static final SuggestionResponse NULL_SUGGESTION_RESPONSE =
      SuggestionResponse.create(Lists.newArrayList(NULL_SUGGESTION_DATA));

  @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();
  private SearchResourceImpl search;

  @Before
  public void setup() {
    search = new SearchResourceImpl(mockSuggestionApi);
  }

  @Test
  public void testCollegeName() throws Exception {
    String collegeName = "Stanford";
    when(mockSuggestionApi.getCollegeSuggestions(collegeName))
        .thenReturn(STANFORD_SUGGESTION_RESPONSE);

    List<SearchData> ret = search.getSearchSuggestions(collegeName);
    CollegeId collegeId = CollegeId.create(STANFORD_SUGGESTION_DATA.id());
    SearchData expected = SearchData.create("Stanford University", collegeId);
    assertThat(ret).containsExactly(expected);
  }

  @Test
  public void testNullCollegeName() throws Exception {
    String collegeName = "Stanford";
    when(mockSuggestionApi.getCollegeSuggestions(collegeName)).thenReturn(NULL_SUGGESTION_RESPONSE);

    List<SearchData> ret = search.getSearchSuggestions(collegeName);
    assertThat(ret).isEmpty();
  }

  @Test
  public void testFilteringIncorrectSuggestions() throws Exception {
    String collegeName = "Stan";
    when(mockSuggestionApi.getCollegeSuggestions(collegeName)).thenReturn(MULTIPLE_SUGGESTION_RESPONSE);
    
    List<SearchData> ret = search.getSearchSuggestions(collegeName);
    CollegeId collegeId = CollegeId.create(STANFORD_SUGGESTION_DATA.id());
    SearchData expected = SearchData.create("Stanford University", collegeId);
    assertThat(ret).containsExactly(expected);
  }
}
