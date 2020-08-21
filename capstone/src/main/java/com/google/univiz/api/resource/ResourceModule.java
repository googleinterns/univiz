package com.google.univiz.api.resource;

import com.google.inject.AbstractModule;
import com.google.univiz.api.MapsResource;

/**
 * Adds bindings for all the resource interfaces. These interfaces sit between the backend layer
 * (CollegeData) and servlets.
 */
public final class ResourceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(MapsResource.class).to(MapsResourceImpl.class);
    bind(SearchResource.class).to(SearchResourceImpl.class);
    bind(VisResource.class).to(VisResourceImpl.class);
  }
}
