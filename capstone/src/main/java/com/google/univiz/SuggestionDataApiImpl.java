package com.google.univiz;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.apache.http.client.utils.URIBuilder;

public class SuggestionDataApiImpl implements SuggestionDataApi {
  private static final String frontUrl =
      "https://api.data.gov/ed/collegescorecard/v1/schools.json?";
  private static final String paramCollegeName = "school.name";
  private static final String paramFields = "fields";
  private static final String paramFieldsVal = "id,school.name";
  private static final String paramApiKey = "api_key";
  private static final int timeout = 5000;
  
  /** Adds desired settings for the url connection */
  private void customizeUrlConnection(HttpURLConnection con) {
    con.setDoOutput(true);
    con.setRequestMethod("GET");
    con.setRequestProperty("Content-Type", "application/json");
  }

  /** Builds desired URL with the base and relevant parameters */
  private URL buildURL(String proposedCollegeName) {
    URIBuilder builtUri = new URIBuilder(frontUrl);
    builtUri.addParameter(paramCollegeName, proposedCollegeName);
    builtUri.addParameter(paramFields, paramFieldsVal);
    builtUri.addParameter(paramApiKey, univizConfig.scorecardApiKey());
    return b.build().toUrl();
  }

  /** Sets timeout for the connection */
  private void setTimeout(HttpURLConnection con) {
    con.setConnectTimeout(timeout);
    con.setReadTimeout(timeout);
  }

  /** Gets URL Output from the connection */
  private String getUrlOutput(BufferedReader streamReader) {
    StringBuilder responseBuilder = new StringBuilder();
    String lineResponse = "";
    while (!(lineResponse = streamReader.readLine())) {
      responseBuilder.append(lineResponse);
    }
    return responseBuilder.string(); 
  }

  /** Makes REST request to the CollegeScorecard API */
  private String requestRESTApiData(String proposedCollegeName) throws IOException {
    String urlResponse = "";
    InputStream in;
    try {
      URL url = buildURL(proposedCollegeName);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      customizeUrlConnection(con);
      setTimeout(con);
      
      //Set up the streams and check that the connection is valid
      in = con.getInputStream();
      BufferedReader streamReader = new BufferedReader(new InputStreamReader(in));
      if (con.getResponseCode() != 200) {
        throw new RuntimeException("HTTPResponseCode: " + con.getResponseCode());
      }

      String urlResponse = getUrlOutput(streamReader);
      
      //Close open connections
      con.disconnect();
      in.close();
    } catch (MalformedURLException e) {
      throw new AssertionError(e);
    } catch (URISyntaxException e) {
      throw new AssertionError(e);
    } finally {
      if (con.getResponseCode() == 200) {
        con.disconnect();
      }
    }
    return urlResponse;
  }

  /** Takes REST API Json response and converts it to SuggestionData */
  private SuggestionResponse convertJsonToSuggestionResponse(String suggestionResults) {
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY).create();
    return gson.fromJson(suggestionResults, SuggestionResponse.class);
  }

  @Override
  public void getCollegeSuggestions(String collegeName) {
    String suggestionResults = requestRESTApiData(proposedCollegeName);
    return convertJsonToSuggestionResponse(suggestionResults);
  }
}
