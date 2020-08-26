package com.google.univiz.servlets;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.univiz.api.representation.SearchData;
import com.google.univiz.api.resource.SearchResource;
import com.google.univiz.common.ServletHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that returns the list of matching college names based on a partial input query. This
 * service is best used for the auto-complete feature.
 */
//@WebServlet("/search")
@Singleton
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

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String numberCollegesStr = request.getParameter("collegeNumber");
    int numberOfColleges = Integer.parseInt(numberCollegesStr);
    List<String> listToSend = new ArrayList<>(); /*List of stringified ids*/
    for (int i = 0; i < numberOfColleges; i++) {
      StringBuilder parameterStr = new StringBuilder();
      parameterStr.append("college");
      parameterStr.append(Integer.toString(i));
      String collegeId = request.getParameter(parameterStr.toString());
      listToSend.add(collegeId);
    }
    
    StringBuffer idsBuffer = new StringBuffer(); 
    for (String id : listToSend) {
      idsBuffer.append(id);
      idsBuffer.append(",");
    }
    StringBuilder redirectLink = new StringBuilder();
    redirectLink.append("/dashboard.html?id="); /*Default link*/
    redirectLink.append(idsBuffer.toString()); /*List of ids*/
    response.sendRedirect(redirectLink.toString());
  }
}
