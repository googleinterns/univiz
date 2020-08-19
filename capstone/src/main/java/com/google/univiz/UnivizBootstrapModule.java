package com.google.univiz;

import com.google.inject.AbstractModule;

final class UnivizBootstrapModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new GsonModule());
  }
}
