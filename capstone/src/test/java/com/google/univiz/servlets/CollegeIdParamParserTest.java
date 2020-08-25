package com.google.univiz.servlets;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import com.google.univiz.api.representation.CollegeId;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(JUnit4.class)
public final class CollegeIdParamParserTest {

  @Rule public final MockitoRule rule = MockitoJUnit.rule();

  @Mock private HttpServletRequest mockRequest;

  @Test
  public void nullQuery_emptyResults() {
    when(mockRequest.getParameter(CollegeIdParamParser.ID_PARAM)).thenReturn(null);

    List<CollegeId> response = CollegeIdParamParser.parseCollegeIds(mockRequest);

    assertThat(response).isEmpty();
  }

  @Test
  public void emptyQuery_emptyResults() {
    when(mockRequest.getParameter(CollegeIdParamParser.ID_PARAM)).thenReturn("");

    List<CollegeId> response = CollegeIdParamParser.parseCollegeIds(mockRequest);

    assertThat(response).isEmpty();
  }

  @Test
  public void singleId_singleResults() {
    when(mockRequest.getParameter(CollegeIdParamParser.ID_PARAM)).thenReturn("1");

    List<CollegeId> response = CollegeIdParamParser.parseCollegeIds(mockRequest);

    assertThat(response).containsExactly(collegeIdOf(1));
  }

  @Test
  public void multipleIds_multipleResults() {
    when(mockRequest.getParameter(CollegeIdParamParser.ID_PARAM)).thenReturn("1,2");

    List<CollegeId> response = CollegeIdParamParser.parseCollegeIds(mockRequest);

    assertThat(response).containsExactly(collegeIdOf(1), collegeIdOf(2));
  }

  private static CollegeId collegeIdOf(int id) {
    return CollegeId.create(id);
  }
}
