package com.example.movie.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movie.entity.MovieOfficial;
import com.example.movie.entity.Room;
import com.example.movie.entity.Schedule;
import com.example.movie.entity.ScheduleDetail;
import com.example.movie.entity.Theater;
import com.example.movie.repository.MovieOfficialRepository;
import com.example.movie.repository.ReservationRepositoryCustom;
import com.example.movie.repository.RoomRepository;
import com.example.movie.repository.ScheduleDetailRepository;
import com.example.movie.repository.ScheduleRepository;
import com.example.movie.repository.ScheduleRepositoryCustom;
import com.example.movie.repository.TheaterRepository;
import com.example.movie.utill.DateUtil;

@Service
public class ScheduleService {

	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	ScheduleDetailRepository scheduleDetailRepository;
	
	@Autowired
	MovieOfficialRepository movieOfficialRepository;
	
	@Autowired
	TheaterRepository theaterRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	ReservationRepositoryCustom reservationRepositoryCustom;
	
	@Autowired
	ScheduleRepositoryCustom scheduleRepositoryCustom;
	
	public Map<String, List<Map<String, String>>> getDate() {
		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String,String>>>();
		
		List<Map<String, String>> date = getDatesDaysWeek(2);
		
		map.put("date", date);
		return map;
	}
	
	//2??? ??????,?????? ??????
	public List<Map<String, String>> getDatesDaysWeek(Integer weeks){
		List<Map<String, String>> dateList = new ArrayList<Map<String,String>>();

		LocalDate startDate = LocalDate.now(); 
		LocalDate endDate= startDate.plusWeeks(weeks);//2?????? ????????????
		Period period = Period.between(startDate,endDate); // ??????????????? ?????????
		int term = period.getDays(); 
	
		//  term ?????? ?????????,?????? ?????????
		for (int i = 0; i < term; i++) {					
			
			String date =  startDate.plusDays(i).toString(); // ???
			//String year =  startDate.plusDays(i).getYear()+""; // ???
			//String month = StringUtils.stripStart(startDate.plusDays(i).getMonth().getValue()+"", "0");  // ????????? 0 ??????
			//String day = StringUtils.stripStart(startDate.plusDays(i).getDayOfMonth()+"", "0"); // ????????? 0??????
			String dayOfWeek = startDate.plusDays(i).getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
			
			Map<String, String> map = new HashMap<String, String>();			
			map.put("date", date);
			map.put("dayOfWeek", dayOfWeek);
			dateList.add(map);		
		}
		return dateList;
	}

	//???????????? ????????? ?????? ???????????? ???????????? ??????,?????????,?????? ????????? ????????????.
	public Map<String, Object> getSchedule() {
		LocalDate now = LocalDate.now();
		String date= now.toString();	
		LocalDateTime dateTime = LocalDateTime.parse(date+" 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));		
		LocalDate lastDate= now.plusWeeks(2);//2??? ????????????
		
		Date startDate = java.sql.Date.valueOf(dateTime.toLocalDate());
		Date endDate = java.sql.Date.valueOf(lastDate);
		//List<Schedule> scheduleList = scheduleRepository.findBySchDateBetween(startDate, endDate);
		List<Schedule> scheduleList = scheduleRepositoryCustom.getDefaultScheule(startDate, endDate);
		
		//??????????????? ??????,???????????? ?????????????????? ????????????
		List<String> movieCdList = scheduleList.stream().map(Schedule::getMovieCd).distinct().collect(Collectors.toList());
		List<Integer> thCodeList = scheduleList.stream().map(Schedule::getThCode).distinct().collect(Collectors.toList());
		//??????, ??????, ?????? ?????? ????????????
		List<MovieOfficial> movieList = movieOfficialRepository.findAllById(movieCdList)
				.stream()
				.sorted(Comparator.comparing(MovieOfficial::getMovieNm))
				.collect(Collectors.toList());
				
		List<Theater> theaterList = theaterRepository.findAllById(thCodeList);
		List<Map<String, String>> dateList = getDatesDaysWeek(2);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("movieList", movieList);
		map.put("theaterList", theaterList);		
		map.put("dateList", dateList);
		
		return map;
	}
	
	public List<MovieOfficial>getMoiveList(List<String> movieCdList, String movideCd,  String sort){
	
		//???????????? ??????????????? ??????
		List<MovieOfficial> movieList = movieOfficialRepository.findAllById(movieCdList)
				.stream()
				.sorted(Comparator.comparing(MovieOfficial::getMovieNm))
				.collect(Collectors.toList());
		if(sort.equals("reservation")) {
		//?????????????????? ????????????	
		}
		
		return movieList;
	}
	
	//??????,??????,?????? ???????????????
	public List<Schedule> selectSchList(String movieCd ,Integer thCode, String schDate) {
		
		List<Schedule> schList = scheduleRepositoryCustom.selectSchList(movieCd, thCode, schDate);			
		return schList;
	}	
	public List<Schedule> getTimeList(String movieCd, Integer thCode, String date) {
	
		
		//??????,??????,?????? ????????? ???????????? ??????????????? ????????????. 
		List<Schedule> scheduleList = scheduleRepositoryCustom.getScheule(movieCd, thCode, date);		
		Predicate<Schedule> schCode = s-> s.getSchCode() == s.getSchCode();		
		scheduleList = scheduleList.stream()
				.filter(schCode)
				.distinct()
				.collect(Collectors.toList());
		
		
		for (int i = 0; i < scheduleList.size(); i++) {
			
			Room room = roomRepository.findByThCodeAndRoomNo(scheduleList.get(i).getThCode(), scheduleList.get(i).getRoomNo());
			
			//???????????? ???????????? 10??????????????? ?????????
			List<ScheduleDetail>schDetailList = scheduleRepositoryCustom.getschDetail(
					scheduleList.get(i).getSchCode()
					, scheduleList.get(i).getMovieCd()
					, scheduleList.get(i).getThCode()
					, date);
			
			scheduleList.get(i).setRoom(room);
			scheduleList.get(i).setScheduleDetail(schDetailList);
			
			for (int j = 0; j <  scheduleList.get(i).getScheduleDetail().size(); j++) {
				
				//???????????????
				Integer rsrvSeatCnt = reservationRepositoryCustom.getRsrvSeatCnt(
						scheduleList.get(i).getScheduleDetail().get(j).getSchCode()
						, scheduleList.get(i).getScheduleDetail().get(j).getSchDetailSeq());
				
				scheduleList.get(i).getScheduleDetail().get(j).setRsrvSeatCnt(rsrvSeatCnt);
				
				//???????????? ????????? ?????? ????????????(10?????????) 
				LocalDateTime now =LocalDateTime.now();			
				Date	schDateTime = scheduleList.get(i).getScheduleDetail().get(j).getSchDetailStart();
				LocalDateTime StartTime = schDateTime.toInstant() // Date -> Instant
						.atZone(ZoneId.systemDefault()) // Instant -> ZonedDateTime
						.toLocalDateTime(); // ZonedDateTime -> LocalDateTime
				//now = now.minusMinutes(10);
				if(StartTime.isEqual(now) || StartTime.isBefore(now)) {
					scheduleList.get(i).getScheduleDetail().get(j).setSchStatus("deadline");
				}
			}
		}
		
		return scheduleList;
	}

	public Map<String, Object> selectSchedule(String movieCd, Integer thCode, String schDate) {
		List<Schedule> schList = scheduleRepositoryCustom.selectSchList(movieCd, thCode, schDate);			
		for (int i = 0; i < schList.size(); i++) {
			
		}
		
		return null;
	}

	public List<Schedule>  getTotalMovieTimeList(String movieCd, String schDate) {
		
		Date SchDate = DateUtil.strToDate(schDate);
			
		List<Schedule> scheduleList = scheduleRepository.findByMovieCdAndSchDate(movieCd, SchDate);
		for (int i = 0; i < scheduleList.size(); i++) {
			
			Room room = roomRepository.findByThCodeAndRoomNo(scheduleList.get(i).getThCode(), scheduleList.get(i).getRoomNo());
			
			//???????????? ???????????? 10??????????????? ?????????
			List<ScheduleDetail>schDetailList = scheduleRepositoryCustom.getTotalMovieTimeList(
					scheduleList.get(i).getSchCode()
					, scheduleList.get(i).getMovieCd()
					, schDate);
			
			scheduleList.get(i).setRoom(room);
			scheduleList.get(i).setScheduleDetail(schDetailList);
			
			for (int j = 0; j <  scheduleList.get(i).getScheduleDetail().size(); j++) {
				
				//???????????????
				Integer rsrvSeatCnt = reservationRepositoryCustom.getRsrvSeatCnt(
						scheduleList.get(i).getScheduleDetail().get(j).getSchCode()
						, scheduleList.get(i).getScheduleDetail().get(j).getSchDetailSeq());
				
				scheduleList.get(i).getScheduleDetail().get(j).setRsrvSeatCnt(rsrvSeatCnt);
				
				//???????????? ????????? ?????? ????????????(10?????????) 
				LocalDateTime now =LocalDateTime.now();			
				Date	schDateTime = scheduleList.get(i).getScheduleDetail().get(j).getSchDetailStart();
				LocalDateTime StartTime = schDateTime.toInstant() // Date -> Instant
						.atZone(ZoneId.systemDefault()) // Instant -> ZonedDateTime
						.toLocalDateTime(); // ZonedDateTime -> LocalDateTime
				//now = now.minusMinutes(10);
				if(StartTime.isEqual(now) || StartTime.isBefore(now)) {
					scheduleList.get(i).getScheduleDetail().get(j).setSchStatus("deadline");
				}
			}
		}
		
		
		return scheduleList;
		
	}
}
