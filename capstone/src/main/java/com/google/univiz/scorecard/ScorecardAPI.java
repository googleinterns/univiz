package com.google.univiz.scorecard;

import java.util.List;
import java.util.Set;

public interface CollegeData {

  List<ScorecardData> getSuggestions(String query);

  List<ScorecardData> getCollegesById(Set<String> ids, Set<String> fieldNames);
}
