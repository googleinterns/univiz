package com.google.univiz;

import java.io.IOException;
import java.io.InputStream;

interface SuggestionApiReaderProvider {
  InputStream getStreamFromUrl(String urlString) throws IOException;
}
