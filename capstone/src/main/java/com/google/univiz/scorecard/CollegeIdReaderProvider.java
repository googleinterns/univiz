package com.google.univiz.scorecard;

import java.io.IOException;
import java.io.InputStream;

interface CollegeIdReaderProvider {

  InputStream getStreamFromUrl(String urlString) throws IOException;
}
