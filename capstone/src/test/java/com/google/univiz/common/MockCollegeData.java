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

  private static final CollegeId STANFORD_COLLEGE_ID = CollegeId.create(243744);
  private static final String STANDFORD_NAME = "Stanford University";
  private static final String STANDFORD_CITY = "Stanford";
  private static final boolean STANFORD_FLAG_MAIN_CAMPUS = true;
  private static final double STANFORD_LATITUDE = 37.429434;
  private static final double STANFORD_LONGITUDE = -122.167359;
  private static final double STANFORD_ADMISSION_RATE = 0.0436;
  private static final double STANFORD_AVG_SAT = 1497.0;
  private static final int STANFORD_NUM_OF_UNDERGRADS = 7083;
  private static final int STANFORD_AVG_COST = 69109;
  private static final double STANFORD_RATIO_OF_MEN = 0.4991;
  private static final double STANFORD_RATIO_OF_WOMEN = 0.5009;

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

  public static CollegeData getStanfordData() {
    CollegeData college =
        CollegeData.builder()
            .setId(STANFORD_COLLEGE_ID)
            .setName(STANDFORD_NAME)
            .setCity(STANDFORD_CITY)
            .setIsMainCampus(STANFORD_FLAG_MAIN_CAMPUS)
            .setLatitude(STANFORD_LATITUDE)
            .setLongitude(STANFORD_LONGITUDE)
            .setCarnegieSizeDegree(CarnegieSizeDegree.getDegree(CARNEGIE_SIZE_DEGREE))
            .setAdmissionRate(STANFORD_ADMISSION_RATE)
            .setAvgSat(STANFORD_AVG_SAT)
            .setNumOfUndergrads(STANFORD_NUM_OF_UNDERGRADS)
            .setAvgCost(STANFORD_AVG_COST)
            .setRatioOfMen(STANFORD_RATIO_OF_MEN)
            .setRatioOfWomen(STANFORD_RATIO_OF_WOMEN)
            .build();
    return college;
  }
}
