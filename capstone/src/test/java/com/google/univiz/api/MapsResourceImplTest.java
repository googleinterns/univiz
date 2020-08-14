package com.google.univiz.api;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.juit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJunit;
import org.mockito.junit.MockitoRule;

@RunWith(JUnit4.class)
public final class MapsResourceImplTest {

    @Mock private CollegeDataApi collegeDataApi;

    @Mock public final MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void testMapsResourceImpl() {

    }
}