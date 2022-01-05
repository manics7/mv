package com.example.movie.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.entity.Reservation;
import com.example.movie.entity.Schedule;
import com.example.movie.service.ReservationService;
import com.example.movie.service.ScheduleService;

@Controller
public class ReservationController {

	@Autowired
	ScheduleService ScheduleService;
	
	@Autowired
	ReservationService reservationService;
	
	ModelAndView modelAndView;
	
	@GetMapping("/rsrv")
	public ModelAndView reservation() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("rsrv/reservation");
		return mv;
	}
			
	@GetMapping("/getRsrv")
	public List<Reservation> rsrv(){
		 return null;
	}
	
	@GetMapping("/rsrvSeat")
	public String rsrvSeat() {
		return "rsrv/reservationSeat";		
		
	}
	
	@PostMapping("/getSeat")
	@ResponseBody
	public Map<String, Object> getSeat(Integer schCode ,Integer schDetailSeq) {
		Map<String, Object>  map = reservationService.getSeat(schCode, schDetailSeq);
		return map;
	}
	
	//@PostMapping("/rsrvSeat")
	public ModelAndView reservationSeat(Integer schCode ,Integer schDetailSeq) {
		modelAndView = new ModelAndView();
			
		Map<String, Object>  map = reservationService.getSeat(schCode, schDetailSeq);
	
		modelAndView.addObject("seatInfo", map);
		modelAndView.setViewName("rsrv/reservationSeat");
		 
		return modelAndView;
	}	
}
