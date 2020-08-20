package com.google.univiz.scorecard;

import com.google.univiz.api.CollegeId;
import java.util.List;

/** Construct a URL string based on the user's preferred colleges. */
interface URLProvider {
   /**
   * Return URL in String form to a remote resource which is either aiming to get college name suggestions or data resources 
   *
   * @param partialCollegeName the partial college name input provided by the client
   * @param ids the optional list of CollegeIds of the client's preferred colleges
   */
  String getUrl(String partialCollegeName, List<CollegeId>... ids);
}
