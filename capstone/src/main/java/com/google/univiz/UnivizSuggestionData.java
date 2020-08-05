package com.google.univiz;

import com.google.auto.value.Autovalue;
import com.google.ryanharter.auto.value.gson.GenerateTypeAdapter;

@AutoValue
abstract class UnivizSuggestionData {
    abstract int id();

    abstract String name();

    abstract String city();

    abstract boolean isMainCampus();

    abstract String urbanizationDegree();

    abstract float latitude();

    abstract float longitude();

    abstract String carnegieSizeDegree();

    abstract float avgSat();

    abstract int numOfUndergrads();

    abstract int avgCost();

    abstract float menRatio();

    abstract float womenRatio();
}