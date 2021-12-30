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

@RestController
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/rsrv")
	public ModelAndView reservation() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("rsrv/reservation");
		return mv;
	}
	
	@GetMapping("/getSchedule")
	public Map<String, Object> getScselectSchListhedule(){
		Map<String, Object> map = reservationService.getSchedule();
		return map;
	}
	
	@GetMapping("/selectSchList")
	public List<Schedule> selectSchList(String movieCd, Integer thCode, String date){
		List<Schedule> list = reservationService.selectSchList(movieCd, thCode, date);
		return list;
	}
	
	@GetMapping("/getSchduleTime")
	public Map<String,Object> getSchduleTime(String movieCd, Integer thCode, String date){
		Map<String,Object> map = reservationService.getSchduleTime(movieCd, thCode, date);
		return map;
	}
		
	@GetMapping("/getRsrv")
	public List<Reservation> rsrv(){
		 List<Reservation> list = reservationService.getRsrvList();
		 return list;
	}
	
	@GetMapping("/rsrvSeat")
	public String reservationSeat() {
		
		return "rsrv/reservationSeat"; 
	}	
}
