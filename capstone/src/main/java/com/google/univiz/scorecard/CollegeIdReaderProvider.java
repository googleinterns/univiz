package com.google.univiz.scorecard;

import com.google.univiz.api.CollegeId;
import java.io.Reader;

interface CollegeIdReaderProvider {
  Reader getReaderFromCollegeId(CollegeId id);
}
