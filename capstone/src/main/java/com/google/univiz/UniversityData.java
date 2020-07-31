package com.google.univiz;

public final class UniversityData {
  private final String name;
  private final String city;
  private final float latitude;
  private final float longitude;
  private final int isMainCampus;
  private final int urbanizationDegree;
  private final float admissionRate;
  private final float avgSAT;
  private final int numOfUndergrads;
  private final int avgCostOfAttendance;
  private final int numOfStudentsInDebt;
  private final int carnegieSizeDegree;
  private final float numOfUndergradMen;
  private final float numOfUndergradWomen;

  public UniversityData(
      String name,
      String city,
      float lat,
      float lon,
      int flagMainCampus,
      int urbanDegree,
      float admisRate,
      float avgSAT,
      int undergradNum,
      int avgCost,
      int studentsInDebt,
      int carnegieSizeDegree,
      float numOfMen,
      float numOfWomen) {
    this.name = name;
    this.city = city;
    this.latitude = lat;
    this.longitude = lon;
    this.isMainCampus = flagMainCampus;
    this.urbanizationDegree = urbanDegree;
    this.admissionRate = admisRate;
    this.avgSAT = avgSAT;
    this.numOfUndergrads = undergradNum;
    this.avgCostOfAttendance = avgCost;
    this.numOfStudentsInDebt = studentsInDebt;
    this.carnegieSizeDegree = carnegieSizeDegree;
    this.numOfUndergradMen = numOfMen;
    this.numOfUndergradWomen = numOfWomen;
  }

  public String getName() {
    return name;
  }

  public String getCity() {
    return city;
  }

  public float getLat() {
    return latitude;
  }

  public float getLong() {
    return longitude;
  }

  public int getMainCampusFlag() {
    return isMainCampus;
  }

  public int getUrbanizationDegree() {
    return urbanizationDegree;
  }

  public float getAdmissionRate() {
    return admissionRate;
  }

  public float getAvgSAT() {
    return avgSAT;
  }

  public int getNumOfUndergrads() {
    return numOfUndergrads;
  }

  public int getAvgCost() {
    return avgCostOfAttendance;
  }

  public int getNumOfStudentsInDebt() {
    return numOfStudentsInDebt;
  }

  public int getCarnegieSizeDegree() {
    return carnegieSizeDegree;
  }

  public float getNumOfMen() {
    return numOfUndergradMen;
  }

  public float getNumOfWomen() {
    return numOfUndergradWomen;
  }
}
