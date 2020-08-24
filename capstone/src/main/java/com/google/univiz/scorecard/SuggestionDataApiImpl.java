package com.google.univiz.scorecard;

import com.google.gson.Gson;
import com.google.univiz.api.representation.SuggestionResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.inject.Inject;

final class SuggestionDataApiImpl implements SuggestionDataApi {
  private final URLProvider urlProvider;
  private final CollegeIdReaderProvider readerProvider;
  private final Gson gson;

  @Inject
  SuggestionDataApiImpl(
      URLProvider urlProvider, CollegeIdReaderProvider readerProvider, Gson gson) {
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
        new InputStreamReader(readerProvider.getStreamFromUrl(urlProvider.getSuggestionUrl(collegeName)));
    return convertJsonToSuggestionResponse(suggestionReader);
  }
}
