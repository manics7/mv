package com.example.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movie.entity.Reservation;
import com.example.movie.entity.Room;
import com.example.movie.entity.Schedule;
import com.example.movie.entity.ScheduleDetail;
import com.example.movie.entity.Seat;
import com.example.movie.repository.ReservationRepository;
import com.example.movie.repository.ReservationRepositoryCustom;
import com.example.movie.repository.RoomRepository;
import com.example.movie.repository.ScheduleDetailRepositoy;
import com.example.movie.repository.ScheduleRepositoy;
import com.example.movie.repository.SeatRepository;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	ReservationRepositoryCustom reservationRepositoryCustom;

	@Autowired
	ScheduleRepositoy scheduleRepositoy;
	
	@Autowired
	ScheduleDetailRepositoy scheduleDetailRepositoy;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	SeatRepository seatRepository;
	
	public Map<String, Object> getSeat(Integer schCode, Integer schDetailSeq) {
		Optional<Schedule> scheduleOpt = scheduleRepositoy.findById(schCode);		
		Optional<ScheduleDetail> schDetailOpt = scheduleDetailRepositoy.findById(schDetailSeq); 
		
		Schedule schedule = scheduleOpt.orElse(null);
		Room room = roomRepository.findByThCodeAndRoomNo(schedule.getThCode(), schedule.getRoomNo());
		
		List<Seat> seatList = seatRepository.findByThCodeAndRoomNo(schedule.getThCode(), schedule.getRoomNo());
		List<Integer>seatNoList = reservationRepositoryCustom.getRsrvSeatNoList(schCode, schDetailSeq);
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("room", room);
		map.put("seatList", seatList);
		map.put("rsrvSeatNoList", seatNoList);
		return map;
	}
	
	public List<Reservation> getRsrvList() {
		List<Reservation> list = reservationRepository.findAll();
		return list;
	}

	
}
