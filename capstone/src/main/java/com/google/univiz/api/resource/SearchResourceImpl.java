package com.google.univiz.api.resource;

import com.google.univiz.api.representation.SearchData;
import java.util.List;

final class SearchResourceImpl implements SearchResource {

  @Override
  public List<SearchData> getSearchSuggestions(String query) {
    throw new UnsupportedOperationException();
  }
}
