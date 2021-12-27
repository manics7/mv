package com.example.movie.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movie.dto.Reservation;
import com.example.movie.dto.Schedule;
import com.example.movie.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired

	
	public Map<String, List<Map<String, String>>> getData() {
		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String,String>>>();
		
		List<Map<String, String>> date = getDefaultDate();
		
		map.put("date", date);
		return map;
	}
	

	public List<Reservation> getRsrvList() {
		// TODO Auto-generated method stub
		List<Reservation> list = reservationRepository.findAll();
		return list;
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
}
