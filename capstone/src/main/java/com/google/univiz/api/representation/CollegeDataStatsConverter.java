package com.google.univiz.api.representation;

import com.google.common.base.Converter;

final class CollegeDataStatsConverter extends Converter<CollegeData, CollegeStats> {

  @Override
  protected CollegeStats doForward(CollegeData college) {
    CollegeStats stats =
        CollegeStats.builder()
            .setAdmissionRate(college.admissionRate())
            .setAvgSat(college.avgSat())
            .setNumOfUndergrads(college.numOfUndergrads())
            .setAvgCost(college.avgCost())
            .setRatioOfMen(college.ratioOfMen())
            .setRatioOfWomen(college.ratioOfWomen())
            .setName(college.name())
            .build();
    return stats;
  }

  @Override
  protected CollegeData doBackward(CollegeStats stats) {
    throw new UnsupportedOperationException();
  }
}
