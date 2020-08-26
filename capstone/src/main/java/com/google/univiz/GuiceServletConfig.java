package com.google.univiz;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.univiz.servlets.CollegeDataVisualizationService;
import com.google.univiz.servlets.MapsService;
import com.google.univiz.servlets.SearchService;

/** Context listener to bootstap Guice based servlets. */
public class GuiceServletConfig extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(
        new UnivizBootstrapModule(),
        new ServletModule() {
          @Override
          protected void configureServlets() {
            serve("/search").with(SearchService.class);
	    serve("/maps").with(MapsService.class);
            serve("/viz/" + CollegeDataVisualizationService.DEADLINES_SUFFIX)
                .with(CollegeDataVisualizationService.class);
            serve("/viz/" + CollegeDataVisualizationService.STATS_SUFFIX)
                .with(CollegeDataVisualizationService.class);
            serve("/viz/" + CollegeDataVisualizationService.TIMELINES_SUFFIX)
                .with(CollegeDataVisualizationService.class);
          }
        });
  }
}
