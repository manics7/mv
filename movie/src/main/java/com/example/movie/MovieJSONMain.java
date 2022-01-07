package com.example.movie;
import java.io.BufferedInputStream;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.example.movie.entity.TempMovie;
import com.example.movie.repository.TempMovieRepository;

public class MovieJSONMain {
	
	@Autowired
	TempMovieRepository tempMovieRepository;
	
//	public void MovieJSONMain() throws Exception{
//         
//         JSONParser jsonparser = new JSONParser();
//         JSONObject jsonobject = (JSONObject)jsonparser.parse(readUrl());
//         JSONObject json =  (JSONObject) jsonobject.get("movieListResult");
//         JSONArray array = (JSONArray)json.get("movieList");
//         for(int i = 0 ; i < array.size(); i++){
//             
//             JSONObject entity = (JSONObject)array.get(i);
//             String movieNm = (String) entity.get("movieNm");
//             String movieCd = (String) entity.get("movieCd");
//             String repGenreNm = (String) entity.get("repGenreNm");
//             String openDt = (String) entity.get("openDt");
//             String genreAlt = (String) entity.get("genreAlt");
//             System.out.print(movieCd + " / ");
//             System.out.print(movieNm + ", ");
//             System.out.print(repGenreNm + ", ");
//             System.out.println(openDt);
//             
//             TempMovie tempMovie = new TempMovie();
//             
//             tempMovie.setMovieCd(movieCd);
//             tempMovie.setMovieNm(movieNm);
//             tempMovie.setGenreNm(repGenreNm);
//             tempMovie.setOpenDt(openDt);
//             tempMovie.setGenreNm(genreAlt);
//             
//             tempMovieRepository.save(tempMovie);
//         }
//         
//         
//         
//    }
//    private static String readUrl() throws Exception {
//
//    	String testDate = "2022";
//    	
//    	BufferedInputStream reader = null;
//        
//        try {
//            URL url = new URL(
////                    "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"
////                    + "searchDailyBoxOfficeList.json"
////                    + "?key=558ab07b627efd244c134a66c1d278c9"
////                    + "&targetDt=" + testDate);
//            		"http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json"
//            		+ "?key=558ab07b627efd244c134a66c1d278c9"
//            		+ "&curPage=1&itemPerPage=100"
//            		+ "&openStartDt=" + testDate);
//            reader = new BufferedInputStream(url.openStream());
//            StringBuffer buffer = new StringBuffer();
//            int i;
//            byte[] b = new byte[4096];
//            while( (i = reader.read(b)) != -1){
//              buffer.append(new String(b, 0, i));
//            }
//            return buffer.toString();
//        } finally {
//            if (reader != null)
//                reader.close();
//        }
//    }
 
    
    public static void main(String[] args) {
    	
    	// controller
        // TODO Auto-generated method stub
        try {
        	
            //new MovieJSONMain();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
}