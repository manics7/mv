//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//
//public class Test {
//
//	String clientID = "write your clinetID";
//	String clientSecret = "write your clinetSecret";
//	String text = null;
//	
//	try	{
//		text = URLEncoder.encode(" 검색어 ", "UTF-8");
//	}
//	catch(UnsupportedEncodingException e) {
//		throw new RuntimeException("검색어 인코딩 실패", e);
//	}
//	String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + text;
//	Map<String, String> requestHeaders = new HashMap<>();
//	requestHeaders.put("X-Naver-Client-Id",clientID);
//	requestHeaders.put("X-Naver-Client-Secret",clientSecret);
//	String responseBody = get(apiURL, requestHeaders);
//	String json = responseBody;
//	JSONParser parser = new JSONParser();
//	JSONObject obj = (JSONObject) parser.parse(json);
//	JSONArray item = (JSONArray) obj.get("items");
//	List<Movie> list = null;
//	list = new ArrayList<Movie>();
//	for(int i = 0;i<item.size();i++) {
//		Movie m = new Movie();
//		JSONObject tmp = (JSONObject) item.get(i);
//		String title = (String) tmp.get("title");
//		String image = (String) tmp.get("image");
//		String description = (String) tmp.get("description");
//		String link = (String) tmp.get("link");
//		String subtitle = (String) tmp.get("subtitle");
//		m.setTitle(title);
//		m.setImage(image);
//		m.setDescription(description);
//		m.setLink(link);
//		m.setSubtitle(subtitle);
//		if(m != null) {
//			list.add(m);
//		}
//	for(Movie movie:list) {
//		System.out.println(movie.getTitle());
//	}
//
//}
