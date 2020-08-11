package com.google.univiz.scorecard;

import com.google.common.base.Converter;
import com.google.univiz.CarnegieSizeDegree;
import com.google.univiz.CollegeData;
import com.google.univiz.CollegeId;

final class ScorecardConverter extends Converter<ScorecardData, CollegeData> {

  @Override
  protected CollegeData doForward(ScorecardData scorecardCollege) {
    CollegeData.Builder collegeBuilder = CollegeData.builder();

    CollegeId collegeId = CollegeId.create(scorecardCollege.id());

    int scorecardCarnegizeSize = scorecardCollege.carnegieSizeDegree();
    if (scorecardCarnegizeSize < 1) {
      CarnegieSizeDegree collegeDataCarnegieSize = CarnegieSizeDegree.getDegree(0);
    } else {
      CarnegieSizeDegree collegeDataCarnegieSize =
          CarnegieSizeDegree.getDegree(scorecardCarnegizeSize);
    }

    int mainCampusInt = scorecardCollege.flagMainCampus();
    if (mainCampusInt == 0) {
      boolean mainCampusBool = true;
    } else {
      boolean mainCampusBool = false;
    }

    // TODO(biancamacias): add set methods for id, flagMainCampus, and carnegieSizeDegree
    CollegeData college =
        collegeBuilder
            .setId(collegeId)
            .setName(scorecardCollege.name())
            .setCity(scorecardCollege.city())
            .setIsMainCampus(mainCampusBool)
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
