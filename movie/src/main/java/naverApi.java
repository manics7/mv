import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class naverApi {

	public static void main(String[] args) throws IOException, ParseException {

		String clientID = "tkX62BB87T2tpzhepVXS";
		String clientSecret = "TBJAEF8YVr";
	
		Scanner scan = new Scanner(System.in);
		System.out.print("검색어 : ");
		
		String searchStr = scan.nextLine();
		
		String text = URLEncoder.encode(searchStr, "UTF-8");
		String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text;
		URL url = new URL(apiURL);
		
		try {
			HttpURLConnection con;
			
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientID);
			con.addRequestProperty("X-Naver-Client-Secret", clientSecret);
			
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if(responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(response.toString());
			JSONArray infoArray = (JSONArray)jsonObject.get("items");
			
			for(int i = 0; i < infoArray.size(); i++ ) {
				JSONObject itemObject = (JSONObject)infoArray.get(i);
				System.out.println("title / " + itemObject.get("title"));
				System.out.println("image / " + itemObject.get("image"));
				System.out.println("director / " + itemObject.get("director"));
				System.out.println("actor / " + itemObject.get("actor"));
				System.out.println("userRating / " + itemObject.get("userRating"));
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
