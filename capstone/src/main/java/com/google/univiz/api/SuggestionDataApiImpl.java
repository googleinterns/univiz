package com.google.univiz.api;

import com.google.gson.Gson;
import com.google.univiz.api.representation.SuggestionResponse;
import com.google.univiz.scorecard.CollegeIdReaderProvider;
import com.google.univiz.scorecard.URLProvider;
import com.google.univiz.api.representation.SuggestionData;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.inject.Inject;

public class SuggestionDataApiImpl implements SuggestionDataApi {
  private final URLProvider urlProvider;
  private final CollegeIdReaderProvider readerProvider;
  private final Gson gson;

  @Inject
  protected SuggestionDataApiImpl(
      URLProvider urlProvider, CollegeIdReaderProvider readerProvider, Gson gson) {
    this.urlProvider = urlProvider;
    this.readerProvider = readerProvider;
    this.gson = gson;
  }

  // Takes REST API Json response and converts it to SuggestionData
  private SuggestionResponse convertJsonToSuggestionResponse(InputStreamReader suggestionReader) {
    return gson.fromJson(suggestionReader, SuggestionResponse.class);
  }

  @Override
  public SuggestionResponse getCollegeSuggestions(String collegeName) throws IOException {
    InputStreamReader suggestionReader =
        new InputStreamReader(readerProvider.getStreamFromUrl(urlProvider.getUrl(collegeName)));
    return convertJsonToSuggestionResponse(suggestionReader);
  }
}
