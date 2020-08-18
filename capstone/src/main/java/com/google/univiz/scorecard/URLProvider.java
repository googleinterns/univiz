package com.google.univiz.scorecard;

import com.google.univiz.api.CollegeId;
import java.util.List;

interface URLProvider {

  String getUrlFromCollegeIds(List<CollegeId> ids);
}
