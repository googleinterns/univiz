package com.google.univiz.common;

import com.google.univiz.api.representation.CarnegieSizeDegree;
import com.google.univiz.api.representation.CollegeData;
import com.google.univiz.api.representation.CollegeId;

/**
 * MockCollegeData is a class with static methods returning CollegeData objects only to be used for
 * testing purposes.
 */
public final class MockCollegeData {

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

  private MockCollegeData() {}
}
