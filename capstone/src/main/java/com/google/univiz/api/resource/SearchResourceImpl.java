package com.google.univiz.api.resource;

import com.google.univiz.api.representation.SearchData;
import java.util.ArrayList;
import java.util.List;

public class SearchResourceImpl implements SearchResource {

  public List<SearchData> getSearchSuggestions(String query) {
    List<SearchData> searchData = new ArrayList<>();
    return searchData;
  }
}
