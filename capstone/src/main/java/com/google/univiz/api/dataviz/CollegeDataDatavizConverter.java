package com.google.univiz.api.dataviz;

import com.google.common.base.Converter;
import com.google.univiz.CollegeData;

final class CollegeDataDatavizConverter extends Converter<CollegeData, CollegeStats> {

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
            .build();
    return stats;
  }

  @Override
  protected CollegeData doBackward(CollegeStats stats) {
    throw new UnsupportedOperationException();
  }
}
