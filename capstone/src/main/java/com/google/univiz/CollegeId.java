package com.google.univiz;

import com.google.auto.value.AutoValue;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

/**
 * CollegeId is a dummy class just to override compilation errors. This may have to be modified in
 * the future.
 */
@AutoValue
@GenerateTypeAdapter
public abstract class CollegeId {

  public abstract int id();

  public static CollegeId create(int id) {
    return new AutoValue(id);
  }
}
