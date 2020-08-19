package com.google.univiz;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

public final class GsonModule extends AbstractModule {

  @Provides
  @Singleton
  Gson provideGson() {
    return new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY).create();
  }
}
