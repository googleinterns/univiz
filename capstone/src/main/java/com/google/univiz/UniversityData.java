package com.google.univiz;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class UniversityData {

  public static UniversityData create(
      String name,
      String city,
      float latitude,
      float longitude,
      int flagMainCampus,
      int urbanizationDegree,
      float admissionRate,
      float avgSAT,
      int numOfUndergrads,
      int avgCost,
      int studentsInDebt,
      int carnegieSizeDegree,
      float numOfMen,
      float numOfWomen) {
    return new UniversityData(
        name,
        city,
        latitude,
        longitude,
        flagMainCampus,
        urbanizationDegree,
        admissionRate,
        avgSAT,
        numOfUndergrads,
        avgCost,
        studentsInDebt,
        carnegieSizeDegree,
        numOfMen,
        numOfWomen);
  }

  public abstract String name();

  public abstract String city();

  public abstract float latitude();

  public abstract float longitude();

  public abstract int flagMainCampus();

  public abstract int urbanizationDegree();

  public abstract float admissionRate();

  public abstract float avgSAT();

  public abstract int numOfUndergrads();

  public abstract int avgCost();

  public abstract int studentsInDebt();

  public abstract int carnegieSizeDegree();

  public abstract float numOfMen();

  public abstract float numOfWomen();
}
