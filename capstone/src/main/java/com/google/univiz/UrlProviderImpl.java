package com.google.univiz;

final class UrlProviderImpl implements UrlProvider {
  private static final String frontUrl =
      "https://api.data.gov/ed/collegescorecard/v1/schools.json?";
  private static final String paramCollegeName = "school.name=";
  private static final String paramFields = "&fields=";
  private static final String paramFieldsVal = "id,school.name";
  private static final String paramApiKey = "&api_key=";
  private final UnivizConfig univizConfig;

  @Inject
  protected UrlProviderImpl(UnivizConfig univizConfig) {
    this.univizConfig = univizConfig;
  }

  @Override
  public String getUrlFromCollegeName(String collegeName) {
    collegeName.replaceAll(" ", "%20");
    StringBuilder urlStringBuilder = new StringBuilder();
    urlStringBuilder.append(paramCollegeName);
    urlStringBuilder.append(collegeName);
    urlStringBuilder.append(paramFields);
    urlStringBuilder.append(paramFieldsVal);
    urlStringBuilder.append(paramApiKey);
    urlStringBuilder.append(univizConfig.scorecardApiKey());
    return urlStringBuilder.toString();
  }
}
