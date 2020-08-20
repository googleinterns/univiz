package com.google.univiz.scorecard;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

class CollegeIdReaderProviderImpl implements CollegeIdReaderProvider {
  public InputStream getStreamFromUrl(String urlString) throws IOException {
    try {
      URL url = new URL(urlString);
      return url.openStream();
    } catch (MalformedURLException e) {
      throw new AssertionError(e);
    }
  }
}
