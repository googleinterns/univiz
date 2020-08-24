package com.google.univiz.common;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class ServletHelperTest {

  private static final Gson GSON = new Gson();

  @Test
  public void writeJsonToResponse_singleString() throws IOException {
    String response = writeJsonToResponse("some input");
    assertThat(response).isEqualTo("\"some input\"");
  }

  @Test
  public void writeJsonToResponse_null() throws IOException {
    String response = writeJsonToResponse(null);
    assertThat(response).isEqualTo("null");
  }

  @Test
  public void writeJsonToResponse_stringArray() throws IOException {
    String response = writeJsonToResponse(Arrays.asList("item 1", "item 2"));
    assertThat(response).isEqualTo("[\"item 1\",\"item 2\"]");
  }

  private String writeJsonToResponse(Object input) throws IOException {
    HttpServletResponse mockResponse = mock(HttpServletResponse.class);
    StringWriter stringWriter = new StringWriter();
    PrintWriter writer = new PrintWriter(stringWriter);
    when(mockResponse.getWriter()).thenReturn(writer);

    ServletHelper.writeJsonToResponse(GSON, mockResponse, input);

    verify(mockResponse).setContentType("application/json");
    return stringWriter.toString();
  }
}
