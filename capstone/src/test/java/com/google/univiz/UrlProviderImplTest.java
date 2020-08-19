package com.google.univiz;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.io.InputStreamReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class UrlProviderImplTest {
  private String createExpectedUrl(String collegeName) {
    StringBuilder urlStringBuilder = new StringBuilder();
  }
  
  @Test
  public void testEmptyCollegeName() {
    String collegeName = "";
    String providedUrl = getUrlFromCollegeName(collegeName);
    String expectedUrl = createExpectedUrl(collegeName);
    assertThat(providedUrl).equals(expectedUrl);
  }

  @Test
  public void testPartialCollegeName() {
    String collegeName = "Sta";
    String providedUrl = getUrlFromCollegeName(collegeName);
    String expectedUrl = createExpectedUrl(collegeName);
    assertThat(providedUrl).equals(expectedUrl);
  }

  @Test
  public void testCollegeNameWithSpace() {
    String collegeName = "Stanford U";
    String providedUrl = getUrlFromCollegeName(collegeName);
    String expectedUrl = createExpectedUrl(collegeName);
    assertThat(providedUrl).equals(expectedUrl);
  }
}
