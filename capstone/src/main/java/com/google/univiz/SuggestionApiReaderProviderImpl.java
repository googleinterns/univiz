package com.google.univiz;

final class SuggestionApiReaderProviderImpl implements SuggestionApiReaderProvider {
  public InputStream getStreamFromUrl(String urlString) throws IOException {
    try {
      URL url = new URL(urlString);
      return url.openStream();
    } catch (MalformedURLException e) {
      throw new AssertionError(e);
    }
  }
}
