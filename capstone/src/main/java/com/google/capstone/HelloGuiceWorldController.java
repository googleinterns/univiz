package com.google.capstone;

import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
public final class HelloGuiceWorldController extends HttpServlet {

  private final String greetee;

  @Inject
  HelloGuiceWorldController(String greetee) {
    this.greetee = greetee;
  }

  @Override
  protected void doGet(HttpServletRequest unused, HttpServletResponse response) throws IOException {
    response.setContentType("text/plain");
    response.getWriter().println(String.format("Hello, %s!", greetee));
  }
}
