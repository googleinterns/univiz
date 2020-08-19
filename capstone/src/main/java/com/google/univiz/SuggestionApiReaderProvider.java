package com.google.univiz;

interface SuggestionApiReaderProvider {
  InputStream getStreamFromUrl(String urlString) throws IOException;
}
