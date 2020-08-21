package com.google.univiz.api.resource;

import static java.util.stream.Collectors.toList;

import com.google.univiz.api.CollegeDataApi;
import com.google.univiz.api.representation.CollegeDataStatsConverter;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.CollegeStats;
import com.google.univiz.api.representation.Deadline;
import com.google.univiz.api.representation.Timeline;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

final class VisResourceImpl implements VisResource {
  private final CollegeDataApi collegeDataApi;
  private final CollegeDataStatsConverter converter;

  @Inject
  VisResourceImpl(CollegeDataApi collegeDataApi, CollegeDataStatsConverter converter) {
    this.collegeDataApi = collegeDataApi;
    this.converter = converter;
  }

  @Override
  public List<CollegeStats> getCollegeStats(List<CollegeId> colleges) throws IOException {
    return collegeDataApi.getCollegesById(colleges).stream()
        .map(converter::convert)
        .collect(toList());
  }

  @Override
  public List<Timeline> getRecommendedTimeline(List<CollegeId> colleges) {
    return new ArrayList<Timeline>();
  }

  @Override
  public List<Deadline> getDeadlines(List<CollegeId> colleges) {
    return new ArrayList<Deadline>();
  }
}
