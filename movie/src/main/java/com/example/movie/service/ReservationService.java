package com.example.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movie.dto.Reservation;
import com.example.movie.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	public List<Reservation> getRsrvList() {
		// TODO Auto-generated method stub
		List<Reservation> list = reservationRepository.findAll();
		return list;
	}
}
