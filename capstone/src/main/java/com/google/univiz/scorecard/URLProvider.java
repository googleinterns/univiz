package com.google.univiz.scorecard;

import com.google.univiz.api.CollegeId;
import java.util.List;

/** Construct a URL string based on the user's preferred colleges. */
interface URLProvider {
  /**
   * Return URL in String form to a remote resource corresponding to the given partialCollegeName
   *
   * @param partialCollegeName the partial college name input provided by the client
   */
  String getUrlFromPartialCollegeName(String partialCollegeName);
  /**
   * Return URL in String form to a remote resource corresponding to the given CollegeIds.
   *
   * @param ids the CollegeIds of the user's preferred colleges
   */
  String getUrlFromCollegeIds(List<CollegeId> ids);
}
