package com.google.univiz.api;

import com.google.univiz.CollegeData;
import com.google.univiz.api.dataviz.Deadline;
import com.google.univiz.api.dataviz.Timeline;
import com.google.univiz.api.dataviz.TuitionStats;
import java.util.List;

/**
 * The VisResource interface is responsible for all data management/processing specific for the vis
 * feature of the UniViz webapp.
 */
public interface VisResource {

  /**
   * For every college the user selected, obtain the raw statistics and compute
   * summary statistics on tuition-related data.
   *
   * @param colleges the raw data for each of the user's selected colleges
   * @return the list of statistics per college.
   */
  List<TuitionStats> getTuitionStats(List<CollegeData> colleges);

  /**
   * For every college the user selected, obtain the recommended set of actions
   * for the user to take and the recommended time range for each action.
   * 
   * @param colleges the raw data for each of the user's selected colleges
   * @return the actions/time range pairs for each college.
   */
  List<Timeline> getRecommendedTimeline(List<CollegeData> colleges);

  /**
   * For every college the user selected, obtain the application deadlines.
   *
   * @param colleges the raw data for each of the user's selected colleges
   * @return the application deadlines (application release/due date) per college.
   */
  List<Deadline> getDeadlines(List<CollegeData> colleges);
}
