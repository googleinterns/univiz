package com.google.univiz.api.resource;

import static java.util.stream.Collectors.toList;

import com.google.univiz.api.CollegeDataApi;
import com.google.univiz.api.representation.CollegeDataStatsConverter;
import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.CollegeStats;
import com.google.univiz.api.representation.Deadline;
import com.google.univiz.api.representation.Timeline;
import java.io.IOException;
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
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Deadline> getDeadlines(List<CollegeId> colleges) {
    Deadline deadline =
        Deadline.builder()
            .setOpeningMonth(9)
            .setOpeningDay(1)
            .setOpeningYear(2020)
            .setClosingMonth(12)
            .setClosingDay(1)
            .setClosingYear(2020)
            .build();
    return colleges.stream().map(college -> deadline).collect(toList());
  }
}
