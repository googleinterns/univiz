package com.google.univiz.scorecard;

import static java.util.stream.Collectors.toList;

import com.google.gson.Gson;
import com.google.univiz.api.CollegeDataApi;
import com.google.univiz.api.representation.CollegeData;
import com.google.univiz.api.representation.CollegeId;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.inject.Inject;

final class CollegeDataApiImpl implements CollegeDataApi {
  private final ScorecardConverter scorecardConverter;
  private final CollegeIdReaderProvider readerProvider;
  private final URLProvider urlProvider;
  private final Gson gson;

  @Inject
  CollegeDataApiImpl(
      ScorecardConverter scorecardConverter,
      CollegeIdReaderProvider readerProvider,
      URLProvider urlProvider,
      Gson gson) {
    this.scorecardConverter = scorecardConverter;
    this.readerProvider = readerProvider;
    this.urlProvider = urlProvider;
    this.gson = gson;
  }

  @Override
  public List<CollegeData> getCollegesById(List<CollegeId> ids) throws IOException {
    InputStreamReader reader =
        new InputStreamReader(
            readerProvider.getStreamFromUrl(urlProvider.getUrlFromCollegeIds(ids)));
    ScorecardResponse scorecardResponse = gson.fromJson(reader, ScorecardResponse.class);
    return scorecardResponse.scorecardData().stream()
        .map(scorecardConverter::convert)
        .collect(toList());
  }
}
