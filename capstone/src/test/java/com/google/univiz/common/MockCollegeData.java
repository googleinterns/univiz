package com.google.univiz.common;

import com.google.univiz.api.representation.CarnegieSizeDegree;
import com.google.univiz.api.representation.CollegeData;
import com.google.univiz.api.representation.CollegeId;

/**
 * MockCollegeData is a class with static methods returning CollegeData objects only to be used for
 * testing purposes.
 */
public final class MockCollegeData {

  private MockCollegeData() {}

  public static CollegeData getNyuData() {
    CollegeData college =
        CollegeData.builder()
            .setId(CollegeId.create(193900))
            .setName("New York University")
            .setCity("New York")
            .setIsMainCampus(true)
            .setLatitude(40.729452)
            .setLongitude(-73.997264)
            .setCarnegieSizeDegree(CarnegieSizeDegree.getDegree(17))
            .setAdmissionRate(0.1999)
            .setAvgSat(1419.0)
            .setNumOfUndergrads(26339)
            .setAvgCost(69830)
            .setRatioOfMen(0.4253)
            .setRatioOfWomen(0.5747)
            .build();
    return college;
  }

  public static CollegeData getStanfordData() {
    CollegeData college =
        CollegeData.builder()
            .setId(CollegeId.create(243744))
            .setName("Stanford University")
            .setCity("Stanford")
            .setIsMainCampus(true)
            .setLatitude(37.429434)
            .setLongitude(-122.167359)
            .setCarnegieSizeDegree(CarnegieSizeDegree.getDegree(17))
            .setAdmissionRate(0.0436)
            .setAvgSat(1497.0)
            .setNumOfUndergrads(7083)
            .setAvgCost(69109)
            .setRatioOfMen(0.4991)
            .setRatioOfWomen(0.5009)
            .build();
    return college;
  }
}
