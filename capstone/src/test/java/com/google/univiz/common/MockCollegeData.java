package com.google.univiz.common;

import com.google.univiz.api.representation.CarnegieSizeDegree;
import com.google.univiz.api.representation.CollegeData;
import com.google.univiz.api.representation.CollegeId;

/**
 * MockCollegeData is a class with static methods returning CollegeData objects only to be used for
 * testing purposes.
 */
public class MockCollegeData {

  private static final CollegeId NYU_COLLEGE_ID = CollegeId.create(193900);
  private static final String NYU_NAME = "New York University";
  private static final String NYU_CITY = "New York";
  private static final boolean NYU_FLAG_MAIN_CAMPUS = true;
  private static final double NYU_LATITUDE = 40.729452;
  private static final double NYU_LONGITUDE = -73.997264;
  private static final int CARNEGIE_SIZE_DEGREE = 17;
  private static final double NYU_ADMISSION_RATE = 0.1999;
  private static final double NYU_AVG_SAT = 1419.0;
  private static final int NYU_NUM_OF_UNDERGRADS = 26339;
  private static final int NYU_AVG_COST = 69830;
  private static final double NYU_RATIO_OF_MEN = 0.4253;
  private static final double NYU_RATIO_OF_WOMEN = 0.5747;

  public static CollegeData getNyuData() {
    CollegeData college =
        CollegeData.builder()
            .setId(NYU_COLLEGE_ID)
            .setName(NYU_NAME)
            .setCity(NYU_CITY)
            .setIsMainCampus(NYU_FLAG_MAIN_CAMPUS)
            .setLatitude(NYU_LATITUDE)
            .setLongitude(NYU_LONGITUDE)
            .setCarnegieSizeDegree(CarnegieSizeDegree.getDegree(CARNEGIE_SIZE_DEGREE))
            .setAdmissionRate(NYU_ADMISSION_RATE)
            .setAvgSat(NYU_AVG_SAT)
            .setNumOfUndergrads(NYU_NUM_OF_UNDERGRADS)
            .setAvgCost(NYU_AVG_COST)
            .setRatioOfMen(NYU_RATIO_OF_MEN)
            .setRatioOfWomen(NYU_RATIO_OF_WOMEN)
            .build();
    return college;
  }
}
