package com.google.univiz.common;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * A set of static utility methods that are common among servlet classes.
 */
public final class ServletHelper {

  public static void writeJsonToResponse(Gson gson, HttpServletResponse response, Object results)
      throws IOException {
    String json = gson.toJson(results);
    response.setContentType("application/json");
    response.getWriter().println(json);
  }

  private ServletHelper() { }
}
