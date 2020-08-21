package com.google.univiz.api.resource;

import com.google.univiz.api.representation.CollegeId;
import com.google.univiz.api.representation.CollegeStats;
import com.google.univiz.api.representation.Deadline;
import com.google.univiz.api.representation.Timeline;
import java.io.IOException;
import java.util.List;

/**
 * The VisResource interface is responsible for all data management/processing specific for the vis
 * feature of the UniViz webapp.
 */
public interface VisResource {

  /**
   * For every college the user selected, obtain the raw statistics and compute summary statistics
   * on college-related data.
   *
   * @param colleges the IDs for each of the user's selected colleges
   * @return the list of statistics per college.
   */
  List<CollegeStats> getCollegeStats(List<CollegeId> colleges) throws IOException;

  /**
   * For every college the user selected, obtain the recommended set of actions for the user to take
   * and the recommended time range for each action.
   *
   * @param colleges the IDs for each of the user's selected colleges
   * @return the actions/time range pairs for each college.
   */
  List<Timeline> getRecommendedTimeline(List<CollegeId> colleges);

  /**
   * For every college the user selected, obtain the application deadlines.
   *
   * @param colleges the IDs for each of the user's selected colleges
   * @return the application deadlines (application release/due date) per college.
   */
  List<Deadline> getDeadlines(List<CollegeId> colleges);
}
