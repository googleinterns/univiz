package com.google.capstone;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

final class CapstoneBootstrapModule extends AbstractModule {
  @Provides
  String provideGreetee() {
    return "Guice";
  }
}
