package com.google.univiz.api;

import com.google.univiz.api.dataviz.Deadline;
import com.google.univiz.api.dataviz.Timeline;
import com.google.univiz.api.dataviz.TuitionStats;
import java.util.List;

/**
 * The VisResource interface is responsible for all data management/processing specific for the vis
 * feature of the UniViz webapp.
 */
public interface VisResource {

  List<TuitionStats> getTuitionStats(List<String> colleges);

  List<Timeline> getRecommendedTimeline(List<String> colleges);

  List<Deadline> getDeadlines(List<String> colleges);
}
