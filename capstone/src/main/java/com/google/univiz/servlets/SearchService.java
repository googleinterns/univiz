package com.google.univiz.servlets;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.univiz.api.representation.SearchData;
import com.google.univiz.api.resource.SearchResource;
import com.google.univiz.common.ServletHelper;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that returns the list of matching college names based on a partial input query. This
 * service is best used for the auto-complete feature.
 */
@WebServlet("/search")
public final class SearchService extends HttpServlet {

  private final Gson gson;
  private final SearchResource searchResource;

  @Inject
  SearchService(Gson gson, SearchResource searchResource) {
    this.gson = gson;
    this.searchResource = searchResource;
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String query = request.getParameter("query");
    if (Strings.isNullOrEmpty(query)) {
      throw new IllegalArgumentException("Expected the query to be a non-empty string");
    }
    List<SearchData> searchResults = searchResource.getSearchSuggestions(query);
    ServletHelper.writeJsonToResponse(gson, response, searchResults);
  }
}
