package com.google.univiz.scorecard;

import com.google.common.base.Converter;
<<<<<<< HEAD

/**
 * ScorecardConverter class returns a representation of ScorecardData as an instance of type
 * CollegeData.
 */
class ScorecardConverter extends Converter<ScorecardData, CollegeData> {

  @Override
  protected CollegeData doForward(ScorecardData scorecardCollege) {
    CollegeData.Builder collegeBuilder = CollegeData.builder();

    Integer scorecardCarnegieSize = scorecardCollege.carnegieSizeDegree();
    if (scorecardCarnegieSize < 1 || scorecardCarnegieSize == null) {
      CarnegieSizeDegree collegeDataCarnegieSize = CarnegieSizeDegree.getDegree(0);
    } else {
      CarnegieSizeDegree collegeDataCarnegieSize =
          CarnegieSizeDegree.getDegree(scorecardCarnegieSize);
    }

    CollegeData college =
        collegeBuilder
            .setId(scorecardCollege.id())
            .setName(scorecardCollege.name())
            .setCity(scorecardCollege.city())
            .setIsMainCampus(scorecardCollege.flagMainCampus())
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
  }

  @Override
  protected ScorecardData doBackward(CollegeData college) {
    throw UnsupportedOperationException();
  }
}
