package com.google.univiz.scorecard;

import com.google.univiz.api.CollegeId;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

interface CollegeIdReaderProvider {
  Reader getReaderFromCollegeIds(List<CollegeId> ids) throws IOException;
}
