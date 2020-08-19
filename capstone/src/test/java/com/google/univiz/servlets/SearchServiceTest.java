package com.google.univiz.servlets;

import static com.google.common.truth.Truth.assertThat;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.inject.Guice;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.univiz.api.SearchData;
import com.google.univiz.api.SearchResource;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(JUnit4.class)
public final class SearchServiceTest {

  @Rule public final MockitoRule rule = MockitoJUnit.rule();

  @Mock @Bind private SearchResource mockSearchResource;
  @Inject private SearchService searchService;

  private static final SearchData STANFORD_COLLEGE_DATA = SearchData.create("stanford", 1);
  private static final SearchData USC_COLLEGE_DATA = SearchData.create("USC", 2);

  @Before
  public void setUp() {
    Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void getSearchResults_singleResult() throws IOException {
    String query = "stan";
    when(mockSearchResource.getSearchSuggestions(query))
        .thenReturn(singletonList(STANFORD_COLLEGE_DATA));

    String returnedJson = doGet(query);

    assertThat(returnedJson).isEqualTo("[{\"collegeName\":\"stanford\",\"collegeId\":1}]\n");
  }

  @Test
  public void getSearchResults_multipleResults() throws IOException {
    String query = "stan";
    when(mockSearchResource.getSearchSuggestions(query))
        .thenReturn(Arrays.asList(STANFORD_COLLEGE_DATA, USC_COLLEGE_DATA));

    String returnedJson = doGet(query);

    assertThat(returnedJson)
        .isEqualTo(
            "[{\"collegeName\":\"stanford\",\"collegeId\":1},"
                + "{\"collegeName\":\"USC\",\"collegeId\":2}]\n");
  }

  @Test
  public void getSearchResults_emptyResult() throws IOException {
    String query = "stan";
    when(mockSearchResource.getSearchSuggestions(query)).thenReturn(emptyList());

    String returnedJson = doGet(query);

    assertThat(returnedJson).isEqualTo("[]\n");
  }

  @Test
  public void getSearchResults_nullQuery_throws() {
    IllegalArgumentException expected =
        assertThrows(IllegalArgumentException.class, () -> doGet(null));

    assertThat(expected).hasMessageThat().startsWith("Expected the query to be a non-empty string");
  }

  @Test
  public void getSearchResults_emptyQuery_throws() {
    IllegalArgumentException expected =
        assertThrows(IllegalArgumentException.class, () -> doGet(""));

    assertThat(expected).hasMessageThat().startsWith("Expected the query to be a non-empty string");
  }

  private String doGet(String query) throws IOException {
    HttpServletRequest mockRequest = mock(HttpServletRequest.class);
    HttpServletResponse mockResponse = mock(HttpServletResponse.class);

    when(mockRequest.getParameter("query")).thenReturn(query);
    StringWriter stringWriter = new StringWriter();
    when(mockResponse.getWriter()).thenReturn(new PrintWriter(stringWriter));

    searchService.doGet(mockRequest, mockResponse);

    return stringWriter.toString();
  }
}
