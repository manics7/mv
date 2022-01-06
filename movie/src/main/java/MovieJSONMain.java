import java.io.BufferedInputStream;
import java.net.URL;

import org.joda.time.TimeOfDay;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
public class MovieJSONMain {
    
    public MovieJSONMain() throws Exception{
         
         JSONParser jsonparser = new JSONParser();
         JSONObject jsonobject = (JSONObject)jsonparser.parse(readUrl());
         JSONObject json =  (JSONObject) jsonobject.get("boxOfficeResult");
         JSONArray array = (JSONArray)json.get("dailyBoxOfficeList");
         for(int i = 0 ; i < array.size(); i++){
             
             JSONObject entity = (JSONObject)array.get(i);
             String movieNm = (String) entity.get("movieNm");
             System.out.println(movieNm);
         }
         
         
    }
    private static String readUrl() throws Exception {

    	String testDate = "20220102";
    	
    	BufferedInputStream reader = null;
        
        try {
            URL url = new URL(
                    "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"
                    + "searchDailyBoxOfficeList.json"
                    + "?key=558ab07b627efd244c134a66c1d278c9"
                    + "&targetDt=" + testDate);
            reader = new BufferedInputStream(url.openStream());
            StringBuffer buffer = new StringBuffer();
            int i;
            byte[] b = new byte[4096];
            while( (i = reader.read(b)) != -1){
              buffer.append(new String(b, 0, i));
            }
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
 
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            new MovieJSONMain();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
}