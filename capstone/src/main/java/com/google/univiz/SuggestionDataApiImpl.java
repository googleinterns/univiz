package com.google.univiz;

import java.io.IOException;
import java.net.HttpUrlConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONObject;

public class SuggestionDataApiImpl implements SuggestionDataApi {
  private static final String frontUrl =
      "https://api.data.gov/ed/collegescorecard/v1/schools.json?";
  private static final String paramCollegeName = "school.name";
  private static final String paramFields = "fields";
  private static final String paramFieldsVal = "id,school.name";
  private static final String paramApiKey = "api_key";
  private static final int timeout = 5000;
  
  /** Adds desired settings for the url connection */
  private void customizeUrlConnection(HttpUrlConnection con) {
    con.setDoOutput(true);
    con.setRequestMethod("GET");
    con.setRequestProperty("Content-Type", "application/json");
  }

  /** Builds desired URL with the base and relevant parameters */
  private URL buildURL(String proposedCollegeName) {
    URIBuilder builtUri = new URIBuilder(frontUrl);
    builtUri.addParameter(paramCollegeName, proposedCollegeName);
    builtUri.addParameter(paramFields, paramFieldsVal);
    builtUri.addParameter(paramApiKey, "PUT_API_KEY_HERE");
    return b.build().toUrl();
  }

  /** Sets timeout for the connection */
  private void setTimeout(HttpUrlConnection con) {
    con.setConnectTimeout(timeout);
    con.setReadTimeout(timeout);
  }

  /** Gets URL Output from the connection */
  private String getUrlOutput(URL url) {
    Scanner scanUrl = new Scanner(url.openStream());
    String urlOutput = "";
    while (scanUrl.hasNext()) {
      urlOutput += scanUrl.nextLine();
    }
    scanUrl.close();
    return urlOutput;
  }

  /** Makes REST request to the CollegeScorecard API */
  private JSONObject requestRESTApiData(String proposedCollegeName) {
    JSONObject jsonData = new JSONObject();
    try {
      URL url = buildURL(proposedCollegeName);
      HttpUrlConnection con = (HttpUrlConnection) url.openConnection();
      customizeUrlConnection(con);
      setTimeout(con);

      con.connect();
      if (con.getResponseCode() != 200) {
        throw new RuntimeException("HTTPResponseCode: " + con.getResponseCode());
      }

      String urlOutput = getUrlOutput(url);
      JSONParser parseJson = new JSONParser();
      jsonData = (JSONObject) parseJson.parse(parseJson);
      con.disconnect();
    } catch (MalformedURLException e) {
      System.out.println("MalformedURLException: " + e);
    } catch (IOException e) {
      System.out.println("IOException: " + e);
    } catch (RuntimeException e) {
      System.out.println("RuntimeException: " + e);
    } catch (URISyntaxException e) {
      System.out.println("URISyntaxException: " + e);
    } finally {
      if (con.getResponseCode() == 200) {
        con.disconnect();
      }
    }
    return jsonData;
  }

  /** Takes REST API Json response and converts it to SuggestionData */
  private List<SuggestionData> convertJsonToSuggestionData(JSONObject suggestionResults) {
    List<SuggestionData> listSuggestions = new ArrayList<>();
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY).create();
    SuggestionData retSuggestions = gson.fromJson(suggestionResults, SuggestionData.class);
    return listSuggestions;
  }

  @Override
  public List<SuggestionData> getSuggestions(String collegeName) {
    JSONObject returnJson = requestRESTApiData(proposedCollegeName);
    List<SuggestionData> returnSuggestionData = convertJsonToSuggestionData(returnJson);
    return returnSuggestionData;
  }
}
