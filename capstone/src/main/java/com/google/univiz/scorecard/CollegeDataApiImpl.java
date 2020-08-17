package com.google.univiz.scorecard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.univiz.CollegeData;
import com.google.univiz.CollegeDataApi;
import com.google.univiz.api.CollegeId;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

final class CollegeDataApiImpl implements CollegeDataApi {
  private final ScorecardConverter scorecardConverter;
  private final CollegeIdReaderProvider readerProvider;
  private final Gson gson;

  @Inject
  protected CollegeDataApiImpl(
      ScorecardConverter scorecardConverter, CollegeIdReaderProvider readerProvider) {
    this.scorecardConverter = scorecardConverter;
    this.readerProvider = readerProvider;
    this.gson = new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY).create();
  }

  @Override
  public List<CollegeData> getCollegesById(List<CollegeId> ids) throws IOException {
    List<CollegeData> colleges = new ArrayList<>();
    // Then deserialize into a ScorecardResponse
    ScorecardResponse scorecardResponse =
        gson.fromJson(readerProvider.getReaderFromCollegeIds(ids), ScorecardResponse.class);
    scorecardResponse.scorecardData().stream()
        .map(scorecard -> scorecardConverter.convert(scorecard))
        .forEach(college -> colleges.add(college));
    return colleges;
  }
}
