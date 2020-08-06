package com.google.univiz.scorecard;

import com.google.common.base.Converter;
import java.util.List;

class ScorecardConverter extends Converter<ScorecardData, CollegeData>{

    @Override
    protected CollegeData doForward(ScorecardData scorecardCollege) {
        
    }

    @Overrride
    protected ScorecardData doBackward(CollegeData college) {throw UnsupportedOperationException();}

    
}

