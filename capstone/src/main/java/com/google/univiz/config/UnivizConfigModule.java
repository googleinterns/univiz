package com.google.univiz.config;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.io.IOException;
import java.io.InputStreamReader;

/** Install to inject Univiz configuration into application classes. */
public final class UnivizConfigModule extends AbstractModule {
  @Provides
  @Singleton
  UnivizConfig provideUnivizConfig() {
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY).create();
    try (InputStreamReader configReader =
        new InputStreamReader(Resources.getResource("univiz_config.json").openStream())) {
      return gson.fromJson(configReader, UnivizConfig.class);
    } catch (IOException e) {
      throw new AssertionError("Failed to load UnivizConfig", e);
    }
  }
}
