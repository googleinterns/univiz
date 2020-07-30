package com.google.univiz;

public final class UniversityData {

  public final class UniversityBuilder {
    private String uniName;
    private String uniCity;
    private float latitude;
    private float longitude;
    private int isMainCampus;
    private int urbanizationDegree;
    private float admissionRate;
    private float avgSAT;
    private int numOfUndergrads;
    private int avgCostOfAttendance;
    private int numOfStudentsInDebt;
    private int carnegieSizeDegree;
    private float numOfUndergradMen;
    private float numOfUndergradWomen;

    public UniversityBuilder setName(String name) {
      this.uniName = name;
      return this;
    }

    public UniversityBuilder setCity(String city) {
      this.uniCity = city;
      return this;
    }

    public UniversityBuilder setLat(float lat) {
      this.latitude = lat;
      return this;
    }

    public UniversityBuilder setLong(float log) {
      this.longitude = log;
      return this;
    }

    public UniversityBuilder setMainCampus(int flag) {
      this.isMainCampus = flag;
      return this;
    }

    public UniversityBuilder setUrbanDegree(int degree) {
      this.urbanizationDegree = degree;
      return this;
    }

    public UniversityBuilder setAdmissionRate(float rate) {
      this.admissionRate = rate;
      return this;
    }

    public UniversityBuilder setAvgSAT(int score) {
      this.avgSAT = score;
      return this;
    }

    public UniversityBuilder setNumberOfUndergrads(int size) {
      this.numOfUndergrads = size;
      return this;
    }

    public UniversityBuilder setAvgCost(int cost) {
      this.avgCostOfAttendance = cost;
      return this;
    }

    public UniversityBuilder setStudentsInDebt(int numberOfStudents) {
      this.numOfStudentsInDebt = numberOfStudents;
      return this;
    }

    public UniversityBuilder setCarnegieDegree(int degree) {
      this.carnegieSizeDegree = degree;
      return this;
    }

    public UniversityBuilder setNumOfMen(float numOfMen) {
      this.numOfUndergradMen = numOfMen;
      return this;
    }

    public UniversityBuilder setNumOfWomen(float numOfWomen) {
      this.numOfUndergradWomen = numOfWomen;
      return this;
    }

    public UniversityData build() {
      return new UniversityData(this);
    }
  }
}
