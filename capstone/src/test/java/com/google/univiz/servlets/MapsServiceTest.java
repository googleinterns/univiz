package com.google.univiz.servlets;

import static com.google.common.truth.Truth.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.MapsData;
import com.google.univiz.api.resource.MapsResource;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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
public final class MapsServiceTest {

  private static final MapsData MAPS_DATA_1 = mapsDataOf("Stanford");
  private static final MapsData MAPS_DATA_2 = mapsDataOf("NYU");

  @Rule public final MockitoRule rule = MockitoJUnit.rule();

  @Mock @Bind private MapsResource mapsResource;

  @Inject private MapsService mapsService;

  @Before
  public void setup() {
    Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void getMapsResults_emptyQuery() throws Exception {
    String response = doGet("");

    assertThat(response).isEqualTo("[]");
  }

  @Test
  public void getMapsResults_nullQuery() throws Exception {
    String response = doGet(null);

    assertThat(response).isEqualTo("[]");
  }

  @Test
  public void getMapsResults_singleResult() throws Exception {
    when(mapsResource.getMapsData(ImmutableList.of(CollegeId.create(1))))
        .thenReturn(ImmutableList.of(MAPS_DATA_1));

    String response = doGet("1");

    assertThat(response).contains("\"name\":\"Stanford\"");
  }

  @Test
  public void getMapsResults_multiResult() throws Exception {
    when(mapsResource.getMapsData(ImmutableList.of(CollegeId.create(1), CollegeId.create(2))))
        .thenReturn(ImmutableList.of(MAPS_DATA_1, MAPS_DATA_2));

    String response = doGet("1,2");

    assertThat(response).contains("\"name\":\"Stanford\"");
    assertThat(response).contains("\"name\":\"NYU\"");
  }

  private String doGet(String idString) throws IOException {
    HttpServletRequest mockRequest = mock(HttpServletRequest.class);
    HttpServletResponse mockResponse = mock(HttpServletResponse.class);

    when(mockRequest.getParameter(CollegeIdParamParser.ID_PARAM)).thenReturn(idString);
    StringWriter stringWriter = new StringWriter();
    when(mockResponse.getWriter()).thenReturn(new PrintWriter(stringWriter));

    mapsService.doGet(mockRequest, mockResponse);

    return stringWriter.toString();
  }

  private static MapsData mapsDataOf(String name) {
    return MapsData.builder()
        .setName(name)
        .setCity("city")
        .setIsMainCampus(true)
        .setLatitude(123.0)
        .setLongitude(234.0)
        .build();
  }
}
