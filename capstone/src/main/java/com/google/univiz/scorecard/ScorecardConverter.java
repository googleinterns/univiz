package com.google.univiz.scorecard;

import com.google.common.base.Converter;
import com.google.univiz.CollegeData;

/**
 * ScorecardConverter class returns a representation of ScorecardData as an instance of type
 * CollegeData.
 */
class ScorecardConverter extends Converter<ScorecardData, CollegeData> {

  @Override
  protected CollegeData doForward(ScorecardData scorecardCollege) {
    CollegeData.Builder collegeBuilder = CollegeData.builder();

    // TODO(biancamacias): add set methods for id, flagMainCampus, and carnegieSizeDegree
    CollegeData college =
        collegeBuilder
            .setName(scorecardCollege.name())
            .setCity(scorecardCollege.city())
            .setLatitude(scorecardCollege.latitude())
            .setLongitude(scorecardCollege.longitude())
            .setAdmissionRate(scorecardCollege.admissionRate())
            .setAvgSat(scorecardCollege.avgSat())
            .setNumOfUndergrads(scorecardCollege.numOfUndergrads())
            .setAvgCost(scorecardCollege.avgCost())
            .setRatioOfMen(scorecardCollege.ratioOfMen())
            .setRatioOfWomen(scorecardCollege.ratioOfWomen())
            .build();
    return college;
  }

  @Override
  protected ScorecardData doBackward(CollegeData college) {
    throw new UnsupportedOperationException();
  }
}
