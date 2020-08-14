package com.google.univiz.scorecard;

import com.google.gson.Gson;
import com.google.univiz.CollegeData;
import com.google.univiz.CollegeDataApi;
import com.google.univiz.api.CollegeId;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class CollegeDataApiImpl implements CollegeDataApi {
  private final ScorecardConverter scorecardConverter;
  private final CollegeIdReaderProvider readerProvider;

  @Inject
  public CollegeDataApiImpl(
      ScorecardConverter scorecardConverter, CollegeIdReaderProvider readerProvider) {
    this.scorecardConverter = scorecardConverter;
    this.readerProvider = readerProvider;
  }

  @Override
  public List<CollegeData> getCollegesById(List<CollegeId> ids) {
    List<CollegeData> colleges = new ArrayList<>();
    for (CollegeId id : ids) {
      // Then deserialize into a ScorecardResponse
      Gson gson = new Gson();
      ScorecardResponse scorecardResponse =
          gson.fromJson(readerProvider.getReaderFromCollegeId(id), ScorecardResponse.class);
      ScorecardData scorecard = scorecardResponse.scorecardData().get(0);
      // Then convert to a CollegeData
      CollegeData college = scorecardConverter.doForward(scorecard);
      colleges.add(college);
    }
    return colleges;
  }
}
