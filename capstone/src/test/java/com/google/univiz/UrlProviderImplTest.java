package com.google.univiz;

import static com.google.common.truth.Truth.assertThat;

import com.google.univiz.config.UnivizConfig;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class UrlProviderImplTest {
  private static final String frontUrl =
      "https://api.data.gov/ed/collegescorecard/v1/schools.json?";
  private static final String paramCollegeName = "school.name=";
  private static final String paramFields = "&fields=";
  private static final String paramFieldsVal = "id,school.name";
  private static final String paramApiKey = "&api_key=";
  private final UnivizConfig univizConfig;
  @Inject private UrlProviderImpl testUrlImpl;

  @Inject
  protected UrlProviderImplTest(UnivizConfig univizConfig) {
    this.univizConfig = univizConfig;
  }

  private String createExpectedUrl(String collegeName) {
    collegeName.replaceAll(" ", "%20");
    StringBuilder urlStringBuilder = new StringBuilder();
    urlStringBuilder.append(paramCollegeName);
    urlStringBuilder.append(collegeName);
    urlStringBuilder.append(paramFields);
    urlStringBuilder.append(paramFieldsVal);
    urlStringBuilder.append(paramApiKey);
    urlStringBuilder.append(univizConfig.scorecardApiKey());
    return urlStringBuilder.toString();
  }

  @Test
  public void testEmptyCollegeName() {
    String collegeName = "";
    String providedUrl = testUrlImpl.getUrlFromCollegeName(collegeName);
    String expectedUrl = createExpectedUrl(collegeName);
    assertThat(providedUrl).equals(expectedUrl);
  }

  @Test
  public void testPartialCollegeName() {
    String collegeName = "Sta";
    String providedUrl = testUrlImpl.getUrlFromCollegeName(collegeName);
    String expectedUrl = createExpectedUrl(collegeName);
    assertThat(providedUrl).equals(expectedUrl);
  }

  @Test
  public void testCollegeNameWithSpace() {
    String collegeName = "Stanford U";
    String providedUrl = testUrlImpl.getUrlFromCollegeName(collegeName);
    String expectedUrl = createExpectedUrl(collegeName);
    assertThat(providedUrl).equals(expectedUrl);
  }
}
