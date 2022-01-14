package com.example.movie.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.movie.dto.MemberDto;
import com.example.movie.entity.MovieOfficial;
import com.example.movie.entity.Reservation;
import com.example.movie.entity.ReservationSeat;
import com.example.movie.entity.Room;
import com.example.movie.entity.Schedule;
import com.example.movie.entity.ScheduleDetail;
import com.example.movie.entity.Seat;
import com.example.movie.repository.MovieOfficialRepository;
import com.example.movie.repository.ReservationRepository;
import com.example.movie.repository.ReservationRepositoryCustom;
import com.example.movie.repository.RoomRepository;
import com.example.movie.repository.ScheduleDetailRepository;
import com.example.movie.repository.ScheduleRepository;
import com.example.movie.repository.SeatRepository;
import com.example.movie.utill.DateUtil;

@Service
public class ReservationService {
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	ReservationRepositoryCustom reservationRepositoryCustom;

	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	ScheduleDetailRepository scheduleDetailRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	MovieOfficialRepository movieOfficialRepository;
	
	@Autowired
	SeatRepository seatRepository;
	
	@Autowired
	EntityManager em;

	@Autowired
	EntityManagerFactory emf;


	public Integer nextRsrvNo(){

		Integer rsrvNo = reservationRepository.lastRsrvNo();

		if(rsrvNo == null){
			rsrvNo = 1;
		}else{
			rsrvNo++;
		}
		return rsrvNo;
	}
	
	public Map<String, Object> getSeat(Integer schCode, Integer schDetailSeq) throws Exception{
		Optional<Schedule> scheduleOpt = scheduleRepository.findById(schCode);		
		Optional<ScheduleDetail> schDetailOpt = scheduleDetailRepository.findById(schDetailSeq); 
		
		Schedule schedule = scheduleOpt.orElse(null);
		Room room = roomRepository.findByThCodeAndRoomNo(schedule.getThCode(), schedule.getRoomNo());
		MovieOfficial movieOfficial = movieOfficialRepository.findById(schedule.getMovieCd()).orElse(null);
		
		List<Seat> seatList = seatRepository.findByThCodeAndRoomNo(schedule.getThCode(), schedule.getRoomNo());
		List<String>seatNoList = reservationRepositoryCustom.getRsrvSeatNoList(schCode, schDetailSeq);
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("room", room);
		map.put("seatList", seatList);
		map.put("rsrvSeatNoList", seatNoList);
		map.put("watchGrade", movieOfficial.getWatchGradeNm());
		return map;
	}
	
	public List<Reservation> getRsrvList() {
		List<Reservation> list = reservationRepository.findAll();
		return list;
	}

	//예약된 좌석과 현재선택한 좌석번호를 비교한다.
	public synchronized void checkSeatNo(Integer schCode, Integer schDetailSeq, List<String> selectSeatNo) throws Exception {
		
		//해당 타임의 예약된 좌석정보를 가져온다.
		List<Reservation> rsrvList=  reservationRepository.findBySchCodeAndSchDetailSeq(schCode, schDetailSeq);
		List<String> rsrvSeatNo = new ArrayList<String>();
		List<String> duplicateSeatNo = new ArrayList<String>();
		
		for (int i = 0; i < rsrvList.size(); i++) {
			
			for (int j = 0; j < rsrvList.get(i).getReservationSeat().size(); j++) {
				 rsrvSeatNo.add(rsrvList.get(i).getReservationSeat().get(j).getSeatNo());
			}
		}
		
		rsrvSeatNo = rsrvSeatNo.stream().distinct().collect(Collectors.toList());
		
		// 선택한 좌석번호와 비교
		for (int i = 0; i < selectSeatNo.size(); i++) {
			
			for (int j = 0; j < rsrvSeatNo.size(); j++) {
				if(selectSeatNo.get(i).equals(rsrvSeatNo.get(j))) {
					duplicateSeatNo.add(selectSeatNo.get(i));		
				}
			}
		}
		
		if(duplicateSeatNo.size() > 0) {
			throw new Exception( selectSeatNo.toString() + "번은 이미 예약된 좌석 입니다.");
		}

	}
	//@Transactional
	//public synchronized Map<String, Object> insertReservation(Map<String, Object> params) {
	public Map<String, Integer> insertReservation(Map<String, Object> params, List<String> selectSeatNo) throws Exception{
		
		//RestTemplate restTemplate = new RestTemplate();
		//boolean result = restTemplate.postForObject("reservation", params, Boolean.class);
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		String schCode =	(String) params.get("schCode");
		String schDetailSeq =	(String) params.get("schDetailSeq");
		String rsrvDate = (String) params.get("date");
		String adultCnt =	(String) params.get("adultCnt");
		String youthCnt =	(String) params.get("youthCnt");
		String price =	(String) params.get("price");
		
		//좌석번호 중복체크
		checkSeatNo(Integer.parseInt(schCode), Integer.parseInt(schDetailSeq), selectSeatNo);
		
		try {
			tx.begin();
						
			Optional<MemberDto> member = Optional.ofNullable((MemberDto)httpSession.getAttribute("userInfo"));
			Optional<String> id = member.map(MemberDto::getM_id);
			
			Reservation reservation = new Reservation();
			List<ReservationSeat>	 seatList = new ArrayList<ReservationSeat>();	
			
			reservation.setSchCode(Integer.parseInt(schCode));
			reservation.setSchDetailSeq(Integer.parseInt(schDetailSeq));
			reservation.setMId(id.orElse(""));
			reservation.setRsrvDate(DateUtil.strToDate(rsrvDate));
			reservation.setAdultCnt(Integer.parseInt(adultCnt));
			reservation.setYouthCnt(Integer.parseInt(youthCnt));
			reservation.setPrice(Integer.parseInt(price));
			reservation.setRsrvStatus(0);
			
			//좌석번호 				
			for (int i = 0; i < selectSeatNo.size(); i++) {
				ReservationSeat seat = new ReservationSeat();
				seat.setSeatNo(selectSeatNo.get(i));
				seatList.add(seat);
			}	
			reservation.setReservationSeat(seatList);					
			reservation =  reservationRepository.save(reservation);			
	
			tx.commit();
			
			map.put("rsrvNo", reservation.getRsrvNo());
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
				
		return map;
	}

	@Transactional
	public void deleteReservation(Integer rsrvNo) {
		reservationRepository.deleteById(rsrvNo);
	}

	public Map<String, Object> getReservationInfo(Integer rsrvNo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Optional<Reservation> reservationOpt = reservationRepository.findById(rsrvNo);
		Reservation reservation = reservationOpt.get();
		
		//최종 결제정보에 보여줄 선택값들 
		Optional<Schedule> scheduleOpt = scheduleRepository.findById(reservation.getSchCode());		
		Optional<ScheduleDetail> schDetailOpt = scheduleDetailRepository.findById(reservation.getSchDetailSeq()); 
		
		Schedule schedule = scheduleOpt.orElse(null);
		ScheduleDetail ScheduleDetail = schDetailOpt.orElse(null);
		
		Room room = roomRepository.findByThCodeAndRoomNo(schedule.getThCode(), schedule.getRoomNo());
		LocalDate localDate = LocalDate.parse(reservation.getRsrvDate().toString());
		String dayOfWeek = localDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
		
		map.put("schedule", schedule);
		map.put("ScheduleDetail", ScheduleDetail);
		map.put("room", room);
		map.put("dayOfWeek", dayOfWeek);			
		map.put("reservation", reservation);
		
		
		return map;
	}

	@Transactional
	public void reservationCancel(Integer rsrvNo) {
		Optional<Reservation> reservationOpt = reservationRepository.findById(rsrvNo);
		Reservation reservation = reservationOpt.orElseThrow();
		
		reservation.setRsrvStatus(1);
		
		reservationRepository.save(reservation);
		
	}
	
}
