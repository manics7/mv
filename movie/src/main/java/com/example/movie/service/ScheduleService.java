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
import com.example.movie.repository.ScheduleRepository;
import com.example.movie.repository.ScheduleRepositoryCustom;
import com.example.movie.repository.TheaterRepository;

@Service
public class ScheduleService {

	@Autowired
	ScheduleRepository scheduleRepository;
	
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
	
	//2주 날짜,요일 생성
	public List<Map<String, String>> getDatesDaysWeek(Integer weeks){
		List<Map<String, String>> dateList = new ArrayList<Map<String,String>>();

		LocalDate startDate = LocalDate.now(); 
		LocalDate endDate= startDate.plusWeeks(weeks);//2주간 마지막날
		Period period = Period.between(startDate,endDate); // 몇일간인지 구하기
		int term = period.getDays(); 
	
		//  term 동안 년월일,요일 구하기
		for (int i = 0; i < term; i++) {					
			
			String date =  startDate.plusDays(i).toString(); // 년
			//String year =  startDate.plusDays(i).getYear()+""; // 년
			//String month = StringUtils.stripStart(startDate.plusDays(i).getMonth().getValue()+"", "0");  // 달에서 0 제외
			//String day = StringUtils.stripStart(startDate.plusDays(i).getDayOfMonth()+"", "0"); // 일에서 0제외
			String dayOfWeek = startDate.plusDays(i).getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
			
			Map<String, String> map = new HashMap<String, String>();			
			map.put("date", date);
			map.put("dayOfWeek", dayOfWeek);
			dateList.add(map);		
		}
		return dateList;
	}

	//처음화면 세팅을 위한 스케쥴에 해당하는 영화,상영관,날짜 목록을 가져온다.
	public Map<String, Object> getSchedule() {
		
		LocalDate now = LocalDate.now();
		String date= now.toString();	
		LocalDateTime dateTime = LocalDateTime.parse(date+" 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));		
		LocalDate lastDate= now.plusWeeks(2);//2주 마지막날
		
		Date startDate = java.sql.Date.valueOf(dateTime.toLocalDate());
		Date endDate = java.sql.Date.valueOf(lastDate);
		
		List<Schedule> scheduleList = scheduleRepository.findBySchDateBetween(startDate, endDate);

		//스케쥴에서 영화,극장코드 중복제거해서 가져오기
		List<String> movieCdList = scheduleList.stream().map(Schedule::getMovieCd).distinct().collect(Collectors.toList());
		List<Integer> thCodeList = scheduleList.stream().map(Schedule::getThCode).distinct().collect(Collectors.toList());
		//영화, 극장, 날짜 목록 가져오기
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
	
		//받아온걸 이름순으로 정렬
		List<MovieOfficial> movieList = movieOfficialRepository.findAllById(movieCdList)
				.stream()
				.sorted(Comparator.comparing(MovieOfficial::getMovieNm))
				.collect(Collectors.toList());
		if(sort.equals("reservation")) {
		//예매율순으로 가져올거	
		}
		
		return movieList;
	}
	
	//영화,극장,일자 선택했을때
	public List<Schedule> selectSchList(String movieCd ,Integer thCode, String schDate) {
		
		List<Schedule> schList = scheduleRepositoryCustom.selectSchList(movieCd, thCode, schDate);			
		return schList;
	}	
	public List<Schedule> getTimeList(String movieCd, Integer thCode, String date) {
	
		
		//영화,극장,일자 조건에 해당하는 상영정보를 가져온다. 
		List<Schedule> scheduleList = scheduleRepositoryCustom.getScheule(movieCd, thCode, date);		
		Predicate<Schedule> schCode = s-> s.getSchCode() == s.getSchCode();		
		scheduleList = scheduleList.stream()
				.filter(schCode)
				.distinct()
				.collect(Collectors.toList());
		
		
		for (int i = 0; i < scheduleList.size(); i++) {
			
			Room room = roomRepository.findByThCodeAndRoomNo(scheduleList.get(i).getThCode(), scheduleList.get(i).getRoomNo());
			
			//시간정보 시작시간 10분뒤까지만 보여줌
			List<ScheduleDetail>schDetailList = scheduleRepositoryCustom.getschDetail(
					scheduleList.get(i).getSchCode()
					, scheduleList.get(i).getMovieCd()
					, scheduleList.get(i).getThCode()
					, date);
			
			scheduleList.get(i).setRoom(room);
			scheduleList.get(i).setScheduleDetail(schDetailList);
			
			for (int j = 0; j <  scheduleList.get(i).getScheduleDetail().size(); j++) {
				
				//예약좌석수
				Integer rsrvSeatCnt = reservationRepositoryCustom.getRsrvSeatCnt(
						scheduleList.get(i).getScheduleDetail().get(j).getSchCode()
						, scheduleList.get(i).getScheduleDetail().get(j).getSchDetailSeq());
				
				scheduleList.get(i).getScheduleDetail().get(j).setRsrvSeatCnt(rsrvSeatCnt);
				
				//예매종료 표시를 위한 시간계산(10분동안) 
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
}
