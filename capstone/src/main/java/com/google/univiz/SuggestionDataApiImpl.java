package com.google.univiz;

import com.google.gson.Gson;
import com.google.univiz.scorecard.SuggestionResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.inject.Inject;

public class SuggestionDataApiImpl implements SuggestionDataApi {
  private final UrlProvider urlProvider;
  private final SuggestionApiReaderProvider readerProvider;
  private final Gson gson;

  @Inject
  protected SuggestionDataApiImpl(
      UrlProvider urlProvider, SuggestionApiReaderProvider readerProvider, Gson gson) {
    this.urlProvider = urlProvider;
    this.readerProvider = readerProvider;
    this.gson = gson;
  }

  /** Takes REST API Json response and converts it to SuggestionData */
  private SuggestionResponse convertJsonToSuggestionResponse(InputStreamReader suggestionReader) {
    return gson.fromJson(suggestionReader, SuggestionResponse.class);
  }

  @Override
  public SuggestionResponse getCollegeSuggestions(String collegeName) throws IOException {
    InputStreamReader suggestionReader =
        new InputStreamReader(
            readerProvider.getStreamFromUrl(urlProvider.getUrlFromCollegeName(collegeName)));
    return convertJsonToSuggestionResponse(suggestionReader);
  }
}
