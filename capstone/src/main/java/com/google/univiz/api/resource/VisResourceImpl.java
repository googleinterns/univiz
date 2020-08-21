package com.google.univiz.api.resource;

import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.CollegeStats;
import com.google.univiz.api.representation.Deadline;
import com.google.univiz.api.representation.Timeline;
import java.util.List;

final class VisResourceImpl implements VisResource {

  @Override
  public List<CollegeStats> getCollegeStats(List<CollegeId> colleges) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Timeline> getRecommendedTimeline(List<CollegeId> colleges) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Deadline> getDeadlines(List<CollegeId> colleges) {
    throw new UnsupportedOperationException();
  }
}
