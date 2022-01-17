package com.example.movie.mapper;

import java.util.List;
import java.util.Map;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.MovieDto;
import com.example.movie.dto.MovieOfficialDto;
import com.example.movie.dto.ReviewMovieDto;
import com.example.movie.dto.RoomDto;
import com.example.movie.dto.ScheduleDetailDto;
import com.example.movie.dto.ScheduleDto;
import com.example.movie.dto.SeatDto;
import com.example.movie.dto.TheaterDto;

public interface BusinessMapper  extends MybatisMapper {
	
	// 사업자 회원가입 아이디 중복체크
	public int buIdCheck(String bid);
	
	// 사업자 회원 가입
	public void businessInsert(BusinessDto business);

	// 사업자 로그인
	public String getb_pw(String b_id);

	// 사업자 검색(세션 저장용?)
	public BusinessDto getBusiness(String b_id);
	
	//영화관 등록
	public void theaterAdd(TheaterDto theater);
	
	//영화관 수정
	public boolean theaterUpdate(TheaterDto theater);
	
	//영화관 정보 검색
	public List<TheaterDto> getTheaterList(String b_id);
	
	//영화관 코드 검색
	public int getTheaterCode(String b_id);
	
	//상영시간표 코드 검색
	public List<ScheduleDto> getScheduleCode(Map<String, Integer> pmap);
	
	//영화 검색
	public List<MovieOfficialDto> getMovieList();
	
	//영화 코드 검색
	public List<ScheduleDto> getMovieCode(Integer sch_code);
	
	//영화 이름 검색
	public List<MovieOfficialDto> getMovieNameList(String movie_cd);
	
	//상영관 번호, 상영관명, 상영관 종류 검색
	public RoomDto getScheduleRoomList(Integer room_no);
	
	//상영관 코드 검색
	public List<ScheduleDto> getRoomCode(Integer sch_code);
	
	//상영관번호 검색
	public List<RoomDto> getRoomNoList(Integer th_code);
	
	//상영관명 검색
	public List<RoomDto> getRoomNameList(Integer th_code);
	
	//상영관종류 검색
	public List<RoomDto> getRoomClassList(Integer th_code);
	
	//상영관 검색(전체)
	public List<RoomDto> getRoomInfoList();
	
	//상영시간표 정보 검색
	public List<ScheduleDto> getScheduleList();
	
	//상영시간표 - 상영날짜 검색
	public List<ScheduleDto> getScheduleDateList(Integer schCode);
	
	//상영시간표(상세) 전체 검색
	public List<ScheduleDetailDto> getScheduleDetailList();
	
	//상영시간표(상세) 시작시간 검색
	public List<ScheduleDetailDto> getScheduleStartTime(Integer schCode);
	
	//상영시간표(상세) 종료시간 검색
	public List<ScheduleDetailDto> getScheduleEndTime(Integer schCode);
	
	//사업자가 등록한 극장이름
	public String selectThNameByBid(String b_id);
	
	//상영관 목록 가져오기
	public List<RoomDto> getRoomList();
	//상영관 삭제하기
	public boolean RoomDelete(Integer roomseq);
	//상영관 등록하기
	public void roomInsert(RoomDto roDto);
	//좌석정보 등록하기
	public void seatInsert(SeatDto seDto);

	// 사업자 영화 등록
	public void movieInsertProc(MovieDto movieDto);

	// 사업자 영화등록에 필요한 th_code를 session에서 b_id로 찾아오기
	public int getThcode(String bId);
	
	//상영시간표 삭제
	public boolean scheduleDelete(Integer sch_code);
	//상영시간표 상세 삭제
	public boolean scheduleDetailDelete(Integer sch_code);
	
	//상영시간표 페이징
	public int getScheduleCount(Integer th_code);
	
	//영화관 정보
	public TheaterDto thUpdateInfo(Integer th_code);
	
	
}
