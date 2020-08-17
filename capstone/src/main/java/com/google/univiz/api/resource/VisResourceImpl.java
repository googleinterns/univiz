package com.google.univiz.api.resource;

import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.Deadline;
import com.google.univiz.api.representation.Timeline;
import com.google.univiz.api.representation.TuitionStats;
import java.util.ArrayList;
import java.util.List;

public class VisResourceImpl implements VisResource {

  public List<TuitionStats> getTuitionStats(List<CollegeId> colleges) {
    List<TuitionStats> stats = new ArrayList<>();
    return stats;
  }

  public List<Timeline> getRecommendedTimeline(List<CollegeId> colleges) {
    List<Timeline> timeline = new ArrayList<>();
    return timeline;
  }

  public List<Deadline> getDeadlines(List<CollegeId> colleges) {
    List<Deadline> deadline = new ArrayList<>();
    return deadline;
  }
}
