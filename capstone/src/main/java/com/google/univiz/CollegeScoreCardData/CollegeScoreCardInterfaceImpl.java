import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

public class CollegeScoreCardInterfaceImpl {
  //TODO: THIS IS A PLACEHOLDER-REPLACE WITH REAL KEY//
  private static final String apiKeyForCollegeScoreCard = "api_key=yeet:^)";
  private static final String apiBeginningURL = "https://api.data.gov/ed/collegescorecard/v1/schools.json?";

  private JSONObject queryCollegeScoreCardAPI(String collegeName,
		  			      String queryPhrase,
		  			      String queryFields) {
    String apiURL = apiBeginningURL + 
	    	        queryPhrase + 
		            queryFields +
		            apiKeyForCollegeScoreCard;
    InputStream readURLData = new URL(apiURL).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(readURLData, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsontext);
      return json;
    } catch (Exception e) {
      System.out.println("Exception caught!");
    } finally {
      readURLData.close(); /*Close InputStream*/
    } 
  }

  public boolean validateCollegeName(String collegeName) {
    /* ASSUMPTIONS:
     * -Each college has a UNIQUE name
     * -Any query which returns multiple college
     *  names will be marked as invalid
     */
    JSONObject collegeNameJson = getCollegeNameJson(collegeName); 
    return (collegeNameJson.metadata.total == 1);
  }

  public List<UniversityData> getCollegesData(List<String> collegeNames) {
    /*Complete Function*/
    List<UniversityData> collegeData = new List<>();
    return collegeData;
  }

  public List<UniversityData> getCollegeSuggestions(String currName) {
    /*Complete Function*/
    List<UniversityData> collegeData = new List<>();
    return collegeData;
  }

  public List<UniversityData> getMapData(List<UniversityData> collegeDatum) {
    /*Complete Function*/
    List<UniversityData> collegeData = new List<>();
    return collegeData;
  }

  public List<UniversityData> getDataVisData(List<UniversityData> collegeDatum) {
    /*Complete Function*/
    List<UniversityData> collegeData = new List<>();
    return collegeData;
  }
}
