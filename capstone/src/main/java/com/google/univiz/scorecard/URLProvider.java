package com.google.univiz.scorecard;

import com.google.univiz.api.representation.CollegeId;
import java.util.List;

/** Construct a URL string based on the user's preferred colleges. */
interface URLProvider {
  /**
   * Return URL in String form to a remote resource which is aiming to get college name
   * suggestions
   *
   * @param partialCollegeName the partial college name input provided by the client
   */
   String getSuggestionUrl(String partialCollegeName);
  
  /**
   * Return URL in String form to a remote resource which is aiming to get data resources
   *
   * @param ids the list of CollegeIds of the client's preferred colleges
   */
   String getDataUrl(List<CollegeId> ids);
}
