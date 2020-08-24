package com.google.univiz.common;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/** A set of static utility methods that are common among servlet classes. */
public final class ServletHelper {

  public static void writeJsonToResponse(Gson gson, HttpServletResponse response, Object result)
      throws IOException {
    response.setContentType("application/json");
    gson.toJson(result, response.getWriter());
  }

  private ServletHelper() {}
}
