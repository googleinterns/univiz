package com.google.univiz.servlets;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.stream.Collectors.toList;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.univiz.api.representation.CollegeId;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

final class CollegeIdParamParser {

  @VisibleForTesting static final String ID_PARAM = "id";

  static List<CollegeId> parseCollegeIds(HttpServletRequest req) {
    String idParam = req.getParameter(ID_PARAM);
    if (isNullOrEmpty(idParam)) {
      return Lists.newArrayList();
    }
    return Splitter.on(',')
        .trimResults()
        .splitToStream(idParam)
        .map(Integer::parseInt)
        .map(CollegeId::create)
        .collect(toList());
  }

  private CollegeIdParamParser() {}
}
