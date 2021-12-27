package com.example.movie.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.dto.Reservation;
import com.example.movie.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/rsrv")
	public ModelAndView reservation() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("rsrv/reservation");
		return mv;
	}
	
	@GetMapping("/modal")
	public String modal() {
		
		return "modal"; 
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
	
	@GetMapping("/getData")
	@ResponseBody
	public Map<String,List<Map<String, String>>> getData() {
		
		Map<String,List<Map<String, String>>> map = reservationService.getData();	
		return map;
	}	
	
}
