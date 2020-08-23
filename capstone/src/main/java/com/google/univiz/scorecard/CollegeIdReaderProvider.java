package com.google.univiz.scorecard;

import java.io.IOException;
import java.io.InputStream;

/** Perform an arbitrary REST call given a URL. */
interface CollegeIdReaderProvider {

  /**
   * Open the InputStream corresponding to the URL given in String form.
   *
   * @param urlString the URL of the remote resource
   * @throws IOException if the stream cannot be opened
   */
  InputStream getStreamFromUrl(String urlString) throws IOException;
}
