package com.google.univiz.servlets;

import static com.google.common.truth.Truth.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class QueryStringUtilTest {

  @Test
  public void emptyQueryString_yieldsEmptyMultimap() {
    assertThat(QueryStringUtil.getQueryParams("")).isEmpty();
  }

  @Test
  public void onlyDelimiters_yieldsEmptyMultimap() {
    assertThat(QueryStringUtil.getQueryParams("&&")).isEmpty();
  }

  @Test
  public void singleParamEmptyVal_yieldsMultimap() {
    assertThat(QueryStringUtil.getQueryParams("foo=")).containsExactly("foo", "");
  }

  @Test
  public void singleParamSingleVal_yieldsMultimap() {
    assertThat(QueryStringUtil.getQueryParams("foo=bar")).containsExactly("foo", "bar");
  }

  @Test
  public void multiParamsSingleVal_yieldsMultimap() {
    assertThat(QueryStringUtil.getQueryParams("foo=bar&fizz=buzz"))
        .containsExactly("foo", "bar", "fizz", "buzz");
  }

  @Test
  public void multiParamsMultiVals_yieldsMultimap() {
    assertThat(QueryStringUtil.getQueryParams("foo=bar,baz&fizz=buzz"))
        .containsExactly("foo", "bar", "foo", "baz", "fizz", "buzz");
  }

  @Test
  public void extraDelimiters_yieldsMultimap() {
    assertThat(QueryStringUtil.getQueryParams("&foo=bar,baz&&fizz=buzz&"))
        .containsExactly("foo", "bar", "foo", "baz", "fizz", "buzz");
  }
}
