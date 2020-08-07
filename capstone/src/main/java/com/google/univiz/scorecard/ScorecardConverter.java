package com.google.univiz.scorecard;

import com.google.common.base.Converter;

/**
 * ScorecardConverter class returns a representation of ScorecardData as an instance of type
 * CollegeData.
 */
class ScorecardConverter extends Converter<ScorecardData, CollegeData> {

  @Override
  protected CollegeData doForward(ScorecardData scorecardCollege) {
    CollegeData.Builer collegeBuilder = CollegeData.builder();

    CollegeData college =
        collegeBuilder
            .setId(scorecardCollege.id())
            .setName(scorecardCollege.name())
            .setCity(scorecardCollege.city())
            .build();
  }

  @Overrride
  protected ScorecardData doBackward(CollegeData college) {
    throw UnsupportedOperationException();
  }
}
