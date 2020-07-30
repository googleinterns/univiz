package com.google.capstone;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*Servlet that addresses search preferences*/
@WebServlet("/search")
public class SearchServlet extends HTTPServlet {
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    /*TODO: Will communicate with the CollegeScoreCard API Lib*/	
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String collegeNamesString = request.getParameter("search");
    String[] collegeNamesList = (collegeNamesString.trim()).split(" ");
    /*TODO: Will forward the names lists to Maps and DataVis Servlet*/
    response.sendRedirct("/index.html");
  }
}
