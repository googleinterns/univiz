package com.google.univiz;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class SuggestionDataApiImpl implements SuggestionDataApi {
  private final UrlProvider urlProvider;
  private final ReaderProvider readerProvider;

  @Inject
  protected SuggestionDataApiImpl(UrlProvider urlProvider, ReaderProvider readerProvider) {
    this.urlProvider = urlProvider;
    this.readerProvider = readerProvider;
  }

  /** Takes REST API Json response and converts it to SuggestionData */
  private SuggestionResponse convertJsonToSuggestionResponse(InputStreamReader suggestionReader) {
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY).create();
    return gson.fromJson(suggestionReader, SuggestionResponse.class);
  }

  @Override
  public SuggestionResponse getCollegeSuggestions(String collegeName) {
    InputStreamReader suggestionReader = new InputStreamReader(readerProvider.getStreamFromUrl(urlProvider.getUrlFromCollegeName(collegeName)));
    String suggestionResults = requestRESTApiData(suggestionReader);
    return convertJsonToSuggestionResponse(suggestionResults);
  }
}
