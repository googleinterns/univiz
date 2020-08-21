package com.google.univiz.api;

import com.google.inject.AbstractModule;

/**
 * Adds bindings for all the interfaces for college data api implementations. They are currently
 * backed by scorecard service.
 */
public final class CollegeApiModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(CollegeDataApi.class).to(CollegeDataApiImpl.class);
    bind(SuggestionDataApi.class).to(SuggestionDataApiImpl.class);
  }
}
