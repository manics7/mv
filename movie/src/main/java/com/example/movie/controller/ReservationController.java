package com.example.movie.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.dto.Reservation;
import com.example.movie.service.ReservationService;

@RestController
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/rsrv")
	public String reservation() {
		
		return "rsrv/reservation"; 
	}
	/**
	 * 오늘부터 2주간 년월일,요일 만들어온다.
	 * @return
	 */
	@GetMapping("/getDate")
	public Map<String,List<Map<String, String>>> getDate() {
		
		Map<String,List<Map<String, String>>> map = reservationService.getDate();	
		return map;
	}	
	
	@GetMapping("/getSchedule")
	public Map<String, Object> getScselectSchListhedule(){
		Map<String, Object> map = reservationService.getSchedule();
		return map;
	}
	
	@GetMapping("/selectSchList")
	public void selectSchList(String movieCd, Integer thCode, String date, String sort){
		//Map<String, Object> map = reservationService.selectSchList(movieCd, thCode, date, sort);
		//return map;
	}
	
	
	
	
	@GetMapping("/getRsrv")
	@ResponseBody
	public List<Reservation> rsrv(){
		 List<Reservation> list = reservationService.getRsrvList();
		 return list;
	}
	
	@GetMapping("/rsrvSeat")
	public String reservationSeat() {
		
		return "rsrv/reservationSeat"; 
	}
}
