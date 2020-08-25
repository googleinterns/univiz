package com.google.univiz;

import com.google.inject.AbstractModule;
import com.google.univiz.api.resource.ResourceModule;
import com.google.univiz.config.UnivizConfigModule;
import com.google.univiz.scorecard.CollegeApiModule;

final class UnivizBootstrapModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new UnivizConfigModule());
    install(new GsonModule());
    install(new ResourceModule());
    install(new CollegeApiModule());
  }
}
