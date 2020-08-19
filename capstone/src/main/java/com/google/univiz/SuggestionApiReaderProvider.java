package com.google.univiz;

import java.io.InputStream;
import java.io.IOException;

interface SuggestionApiReaderProvider {
  InputStream getStreamFromUrl(String urlString) throws IOException;
}
