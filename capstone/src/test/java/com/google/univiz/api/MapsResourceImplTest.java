package com.google.univiz.api;

import com.google.inject.Guice;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.univiz.CollegeDataApi;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

@RunWith(JUnit4.class)
public final class MapsResourceImplTest {

  @Rule public final MockitoRule rule = MockitoJUnit.rule();

  @Bind @Mock private CollegeDataApi collegeDataApi;

  @Bind private CollegeDataConverter converter = new CollegeDataConverter();

  @Inject private MapsResourceImpl mapsImpl;

  @Before
  public void guiceSetUp() {
    Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void testMapsResourceImpl() {}
}
