package com.google.univiz.servlets;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.resource.MapsResource;
import com.google.univiz.common.ServletHelper;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
public final class MapsService extends HttpServlet {

  private final MapsResource mapsResource;
  private final Gson gson;

  @Inject
  MapsService(MapsResource mapsResource, Gson gson) {
    this.mapsResource = mapsResource;
    this.gson = gson;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    List<CollegeId> collegeIds = CollegeIdParamParser.parseCollegeIds(req);
    List<? extends Object> response = mapsResource.getMapsData(collegeIds);

    ServletHelper.writeJsonToResponse(gson, resp, ImmutableList.copyOf(response));
  }
}
