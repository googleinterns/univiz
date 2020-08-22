package com.google.univiz.scorecard;

import com.google.inject.AbstractModule;
import com.google.univiz.api.CollegeDataApi;
import com.google.univiz.api.SuggestionDataApi;

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
