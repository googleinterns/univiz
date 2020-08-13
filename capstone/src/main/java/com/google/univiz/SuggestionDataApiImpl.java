package com.google.univiz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpUrlConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.json.simple.JSONObject;
import org.apache.http.client.utils.URIBuilder;
public class SuggestionDataApiImpl implements SuggestionDataApi {
  private static final frontUrl = "https://api.data.gov/ed/collegescorecard/v1/schools.json?";
  private static final paramCollegeName = "school.name";
  private static final paramFields = "fields";
  private static final paramFieldsValId = "id";
  private static final paramFieldsValName = "school.name";
  private static final int timeout = 5000;
  
  private boolean customizeUrlConnection (HttpUrlConnection con) {
    con.setDoOutput(true);
    con.setRequestMethod("GET");
    con.setRequestProperty("Content-Type", "application/json");
  }

  private boolean addUrlParameters (HttpUrlConnection con) {
    Map<String, String> parameters = new HashMap<>();
    parameters.put(paramFields, paramFieldsValId);
    parameters.put(paramFields, paramFieldsValName);
    parameters.put(paramCollegeName, proposedCollegeName);

    DataSetOutput out = new DataOutputStream(con.getOutputStream());
    out.writeBytes(/*TODO*/);
    out.flush();
    out.close();
  }

  private boolean setTimeout (HttpUrlConnection con) {
    con.setConnectTimeout(timeout);
    con.setReadTimeout(timeout);
  }

  /** Makes REST request to the CollegeScorecard API  */
  private JSONObject requestRESTApiData(String proposedCollegeName) {
    JSONObject jsonData = new JSONObject();
    try {
      URL url = new URL(frontUrl);
      HttpUrlConnection con = (HttpUrlConnection) url.openConnection();
      customizeUrlConnection(con);
      addUrlParameters(con);
      setTimeout(con);

      con.connect();
      if (con.getResponseCode() != 200) {
         throw new RuntimeException("HTTPResponseCode: " + con.getResponseCode());
      }
      JSONParser parseJson = new JSONParser();
      JSONObject retJson = (JSONObject) parseJson.parse(/*TODO*/);
      con.disconnect();
    } catch (MalformedURLException e) {
      /*handle malformed URL Exceptions*/
    } catch (IOException e) {
      /*handle IOExceptions*/
    } catch (RuntimeException e) {
      /*handle RuntimeException*/
    } finally {
      if (con.getResponseCode() == 200) {
        con.disconnect();
      }
    }
    
    //TODO: Move this stuff up or out
    DataSetOutput out = new DataOutputStream(con.getOutputStream());
    out.writeBytes(/*TODO*/);
    out.flush();
    out.close();

    //Reads the content from the URL
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer content = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      content.append(inputLine);
    }
    in.close();
    int status = con.getResponseCode();
 
    Reader streamReader = null;
    if (status > 299) {
      streamReader = new InputStreamReader(con.getErrorStream());
    } else {
      streamReader = new InputStreamReader(con.getInputStream());
    }
  }

  /** Takes REST API Json response and converts it to SuggestionData */
  private List<SuggestionData> convertJsonToSuggestionData(JSONObject suggestionResults) {
  
  }
  
  @Override
  public List<SuggestionData> getSuggestions(String collegeName) {
    JSONObject returnJson = requestRESTApiData(proposedCollegeName);
    List<SuggestionData> returnSuggestionData = convertJsonToSuggestionData(returnJson);
    return returnSuggestionData;
  }
}
