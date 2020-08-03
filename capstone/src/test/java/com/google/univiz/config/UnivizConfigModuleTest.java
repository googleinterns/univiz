package com.google.univiz.config;

import static com.google.common.truth.Truth.assertThat;

import com.google.inject.Guice;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class UnivizConfigModuleTest {

  @Inject private UnivizConfig univizConfig;

  @Before
  public void setup() {
    Guice.createInjector(new UnivizConfigModule()).injectMembers(this);
  }

  @Test
  public void scorecardApiKey_deserializes() {
    assertThat(univizConfig.scorecardApiKey()).isEqualTo("scorecard_test_key");
  }
}
