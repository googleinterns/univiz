package com.google.univiz.scorecard;

import com.google.univiz.api.CollegeId;
import java.io.Reader;
import java.io.IOException;

interface CollegeIdReaderProvider {
  Reader getReaderFromCollegeId(CollegeId id) throws IOException;
}
