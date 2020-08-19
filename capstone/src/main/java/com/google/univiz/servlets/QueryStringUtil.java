package com.google.univiz.servlets;

import com.google.common.base.Splitter;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.Map;

final class QueryStringUtil {

  static Multimap<String, String> getQueryParams(String queryString) {
    Map<String, String> rawQueryMap =
        Splitter.on('&')
            .trimResults()
            .omitEmptyStrings()
            .withKeyValueSeparator('=')
            .split(queryString);
    Multimap<String, String> queryMap = HashMultimap.create();
    rawQueryMap.forEach((key, value) -> parseQueryValues(key, value, queryMap));
    return queryMap;
  }

  private static void parseQueryValues(String key, String value, Multimap<String, String> map) {
    Splitter.on(',').trimResults().splitToStream(value).forEach(v -> map.put(key, v));
  }

  private QueryStringUtil() {}
}
