package com.example.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.movie.dto.Reservation;
import com.example.movie.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/rsrv")
	public String reservation() {
		
		return "rsrv/reservation"; 
	}
	
	@GetMapping("/modal")
	public String modal() {
		
		return "modal"; 
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
