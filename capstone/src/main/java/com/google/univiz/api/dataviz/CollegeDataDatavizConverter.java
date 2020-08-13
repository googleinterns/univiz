package com.google.univiz.api.dataviz;

import com.google.common.base.Converter;
import com.google.univiz.CollegeData;

final class CollegeDataDatavizConverter extends Converter<CollegeData, TuitionStats> {

  @Override
  protected TuitionStats doForward(CollegeData college) {
    TuitionStats stats =
        TuitionStats.builder()
            .setCarnegieSizeDegree(college.carnegieSizeDegree)
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
  protected CollegeData doBackward(TuitionStats stats) {
    throw new UnsupportedOperationException();
  }
}