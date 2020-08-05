package com.google.univiz.scorecard;

import java.util.List;
import java.util.Set;

public interface CollegeData {

  List<SuggestionData> getSuggestions(String query);

  List<SuggestionData> getCollegesById(Set<String> ids, Set<String> names);
}
