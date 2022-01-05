package com.example.movie;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.movie.common.AwsS3;
import com.example.movie.repository.ReservationRepository;
import com.example.movie.sample.BonusMapper;


class MapperTest {

	@Autowired
	BonusMapper bonusMapperl;
	
	@Autowired
	ReservationRepository repository;
	
	@Autowired
	AwsS3 awsS3;
	
	
	
	//날짜 구하기
	//@Test
	void inItDate() {
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
			
			String year =  startDate.plusDays(i).getYear()+""; // 년
			String month = StringUtils.stripStart(startDate.plusDays(i).getMonth().getValue()+"", "0");  // 달에서 0 제외
			String day = StringUtils.stripStart(startDate.plusDays(i).getDayOfMonth()+"", "0"); // 일에서 0제외
			String dayOfWeek = startDate.plusDays(i).getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
			
			//System.out.println(year + " / " + month + "/" + day);						
			Map<String, String> map = new HashMap<String, String>();			
			map.put("date", year+month+day);
			map.put("dayOfWeek", dayOfWeek);
			dateList.add(map);		
		}
		
		System.out.println(dateList.toString());
	}
	
	@Test
	void StringToDate() {
		LocalDate now = LocalDate.now();
		//String date= now.toString();		
		//LocalDateTime dateTime = LocalDateTime.parse(date+" 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		//System.out.println(dateTime.toString());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREA);
		LocalDate date = LocalDate.parse("2021-12-29", formatter);
		LocalDateTime dateTime = LocalDateTime.parse(date+" 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));		
		
		Date startDate = java.sql.Date.valueOf(dateTime.toLocalDate());
		System.out.println("//////////////////// "+startDate);
	}
	
	@Test
	void getUrl() {
	}

}
