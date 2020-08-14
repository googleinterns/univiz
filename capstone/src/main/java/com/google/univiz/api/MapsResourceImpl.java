package com.google.univiz.api;

import com.google.univiz.CollegeData;
import com.google.univiz.CollegeDataApi;
import com.google.univiz.CollegeId;
import java.util.List;
import java.util.ArrayList;
import javax.inject.Inject;

public class MapsResourceImpl implements MapsResource {
    private final CollegeDataApi colleges;
    
    @Inject
    public MapsResourceImpl(CollegeDataApi colleges) {
        this.colleges = colleges;
    }

    @Override
    public List<MapsData> getMapData(List<CollegeId> ids) {
        
    }
}