package com.google.univiz.scorecard;

import com.google.common.base.Converter;
import com.google.univiz.api.representation.CollegeId;

final class ScorecardConverter extends Converter<ScorecardData, CollegeData> {

  @Override
  protected CollegeData doForward(ScorecardData scorecardCollege) {
    CollegeId collegeId = CollegeId.create(scorecardCollege.id());
    CarnegieSizeDegree collegeDataCarnegieSize =
        CarnegieSizeDegree.getDegree(scorecardCollege.carnegieSizeDegree());
    boolean isMainCampus = scorecardCollege.flagMainCampus() == 1;

    CollegeData college =
        CollegeData.builder()
            .setId(collegeId)
            .setName(scorecardCollege.name())
            .setCity(scorecardCollege.city())
            .setIsMainCampus(isMainCampus)
            .setLatitude(scorecardCollege.latitude())
            .setLongitude(scorecardCollege.longitude())
            .setCarnegieSizeDegree(collegeDataCarnegieSize)
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
