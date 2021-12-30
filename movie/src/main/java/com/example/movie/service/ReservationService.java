package com.example.movie.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.movie.common.AwsS3;
import com.example.movie.dto.MovieOfficial;
import com.example.movie.dto.Reservation;
import com.example.movie.dto.Schedule;
import com.example.movie.dto.Theater;
import com.example.movie.repository.MovieOfficialRepository;
import com.example.movie.repository.ReservationRepository;
import com.example.movie.repository.ScheduleRepositoy;
import com.example.movie.repository.TheaterRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class ReservationService {

	@Autowired
	EntityManager entityManager;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	ScheduleRepositoy scheduleRepositoy;

	@Autowired
	MovieOfficialRepository movieOfficialRepository;

	@Autowired
	TheaterRepository theaterRepository;

	@Autowired
	AwsS3 awsS3;

	public Map<String, List<Map<String, String>>> getDate() {
		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String,String>>>();

		List<Map<String, String>> date = getDefaultDate();

		map.put("date", date);
		return map;
	}

	public List<Map<String, String>> getDefaultDate(){
		List<Map<String, String>> dateList = new ArrayList<Map<String,String>>();
		List<DayOfWeek>  days = new ArrayList<DayOfWeek>();
		//DayOfWeek.of(0)
		LocalDate startDate = LocalDate.now(); 
		LocalDate endDate= startDate.plusWeeks(2);//2주간 마지막날
		Period period = Period.between(startDate,endDate); // 몇일간인지 구하기
		int term = period.getDays(); 

		//DayOfWeek dayOfWeek = startDate.getDayOfWeek();		
		//  term 동안 년월일,요일 구하기
		for (int i = 0; i < term; i++) {					

			String date =  startDate.plusDays(i).toString(); // 년
			//String year =  startDate.plusDays(i).getYear()+""; // 년
			//String month = StringUtils.stripStart(startDate.plusDays(i).getMonth().getValue()+"", "0");  // 달에서 0 제외
			//String day = StringUtils.stripStart(startDate.plusDays(i).getDayOfMonth()+"", "0"); // 일에서 0제외
			String dayOfWeek = startDate.plusDays(i).getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);

			//System.out.println(year + " / " + month + "/" + day);						
			Map<String, String> map = new HashMap<String, String>();			
			map.put("date", date);
			map.put("dayOfWeek", dayOfWeek);
			dateList.add(map);		
		}
		return dateList;
	}

	//처음화면 세팅을 위한 스케쥴에 해당하는 영화,상영관,날짜 목록을 가져온다.
	public Map<String, Object> getSchedule() {

		LocalDate now = LocalDate.now();
		String date= now.toString();	
		LocalDateTime dateTime = LocalDateTime.parse(date+" 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));		
		LocalDate lastDate= now.plusWeeks(2);//2주간 마지막날

		Date startDate = java.sql.Date.valueOf(dateTime.toLocalDate());
		Date endDate = java.sql.Date.valueOf(lastDate);

		List<Schedule> scheduleList = scheduleRepositoy.findBySchDateBetween(startDate, endDate);

		//스케쥴에서 영화,극장코드 중복제거해서 가져오기
		List<String> movieCdList = scheduleList.stream().map(Schedule::getMovieCd).distinct().collect(Collectors.toList());
		List<Integer> thCodeList = scheduleList.stream().map(Schedule::getThCode).distinct().collect(Collectors.toList());

		//영화, 극장, 날짜 목록 가져오기
		List<MovieOfficial> movieList = movieOfficialRepository.findAllById(movieCdList);
		List<Theater> theaterList = theaterRepository.findAllById(thCodeList);
		List<Map<String, String>> dateList = getDefaultDate();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("movieList", movieList);
		map.put("theaterList", theaterList);		
		map.put("dateList", dateList);

		//String url = awsS3.getFileURL(awsS3.bucket, awsS3.bucketURL+"mm.thumb.jpg");
		//String url2 = awsS3.getFileURL(awsS3.bucket, awsS3.bucketURL+"82479_320.jpg");	
		//String url3 = awsS3.getFileURL(awsS3.bucket, awsS3.bucketURL+"84949_320.jpg");	
		//System.out.println(url1 + "//////////////////////////////////////////////////");
		//System.out.println(url2 + "//////////////////////////////////////////////////");
		//System.out.println(url3 + "//////////////////////////////////////////////////");

		return map;
	}

	public List<MovieOfficial>getMoiveList(List<String> movieCdList, String movideCd,  String sort){

		//받아온걸 이름순으로 정렬
		List<MovieOfficial> movieList = movieOfficialRepository.findAllById(movieCdList)
				.stream()
				.sorted(Comparator.comparing(MovieOfficial::getMovieNm))
				.collect(Collectors.toList());
		if(sort.equals("reservation")) {
			//예매율순으로 가져올거	
		}

		return movieList;
	}

	//영화,극장,일자 선택했을때
	public Map<String, Object>  selectSchList(String movideCd ,Integer thCode, String date,  String sort) {

		//List<Schedule> scheduleList = getSchedule();
		//List<String> movieCdList = scheduleList.stream().map(Schedule::getMovieCd).distinct().collect(Collectors.toList());		
		//List<MovieOfficial> movieList = getMoiveList(movieCdList,movideCd, sort);
		//List<Map<String, String>> date = getDefaultDate(); 


		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("movieList", movieList);
		//map.put("date", date);
		if(!movideCd.equals("") && !ObjectUtils.isEmpty(thCode) && !date.equals("")) {
			//세개다 선택이면 시간불러온다.
		}
		return map;
	}

	public List<Reservation> getRsrvList() {
		List<Reservation> list = reservationRepository.findAll();
		return list;
	}

}
