package com.example.movie.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.entity.Reservation;
import com.example.movie.entity.Schedule;
import com.example.movie.service.ReservationService;
import com.example.movie.service.ScheduleService;

@RestController
public class ScheduleController {

	@Autowired
	ScheduleService ScheduleService;
		
	@GetMapping("/getSchedule")
	public Map<String, Object> getScselectSchListhedule(){
		Map<String, Object> map = ScheduleService.getSchedule();
		return map;
	}
	
	@GetMapping("/selectSchList")
	public List<Schedule> selectSchList(String movieCd, Integer thCode, String date){
		List<Schedule> list = ScheduleService.selectSchList(movieCd, thCode, date);
		return list;
	}
	
	@GetMapping("/getTimeList")
	public List<Schedule> getSchduleTime(String movieCd, Integer thCode, String date) {
		List<Schedule> list = ScheduleService.getTimeList(movieCd, thCode, date);
		return list;
	}	
}
