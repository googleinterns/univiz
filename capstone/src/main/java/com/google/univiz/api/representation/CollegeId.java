package com.google.univiz.api;

import com.google.auto.value.AutoValue;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

/**
 * CollegeId is a class that represents the unique ID for a single college/university/institution.
 */
@AutoValue
@GenerateTypeAdapter
public abstract class CollegeId {

  public abstract int id();

  public static CollegeId create(int id) {
    return new AutoValue_CollegeId(id);
  }
}
