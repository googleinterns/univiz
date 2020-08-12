package com.google.univiz.api;

/** SuggestionData represents the college data specific to the Search feature */
public class SuggestionData {
  private String name;
  private int id;

  public SuggestionData(String name, int id) {
    this.name = name;
    this.id = id;
  }

  public String collegeName() {
    return name;
  }

  public int collegeId() {
    return id;
  }
}
