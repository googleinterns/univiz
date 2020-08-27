package com.google.univiz.scorecard;

import com.google.gson.Gson;
import com.google.univiz.api.representation.MetadataReader;
import com.google.univiz.api.representation.SuggestionResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.inject.Inject;

final class SuggestionDataApiImpl implements SuggestionDataApi {
  private static final int defaultFirstPage = 0;
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

  private int getPagesToSearch(InputStreamReader suggestionReader) {
    MetadataReader metadata = gson.fromJson(suggestionReader, MetadataReader.class);
    return metadata.content().total() % 100 == 0
        ? metadata.content().total() / 100
        : metadata.content().total() / 100 + 1;
  }

  /** Takes REST API Json response and converts it to SuggestionData */
  private SuggestionResponse convertJsonToSuggestionResponse(InputStreamReader suggestionReader) {
    return gson.fromJson(suggestionReader, SuggestionResponse.class);
  }

  private SuggestionResponse getPageResults(String collegeName, int currentPage)
      throws IOException {
    InputStreamReader suggestionReader =
        new InputStreamReader(
            readerProvider.getStreamFromUrl(
                urlProvider.getSuggestionUrl(collegeName, Integer.toString(currentPage))));
    return convertJsonToSuggestionResponse(suggestionReader);
  }

  @Override
  public SuggestionResponse getCollegeSuggestions(String collegeName) throws IOException {
    InputStreamReader suggestionReader =
        new InputStreamReader(
            readerProvider.getStreamFromUrl(
                urlProvider.getSuggestionUrl(collegeName, Integer.toString(defaultFirstPage))));
    int numberOfPages = getPagesToSearch(suggestionReader);
    System.out.println("NUMBER OF PAGES: ");
    System.out.println(numberOfPages);
    SuggestionResponse retSuggestionResponse = getPageResults(collegeName, 0);
    for (int i = 1; i < numberOfPages; i++) {
      SuggestionResponse currPageSuggestionResponse = getPageResults(collegeName, i);
      retSuggestionResponse.suggestions().addAll(currPageSuggestionResponse.suggestions());
    }
    return retSuggestionResponse;
  }
}
