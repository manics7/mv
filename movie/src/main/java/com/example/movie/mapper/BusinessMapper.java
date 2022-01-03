package com.example.movie.mapper;

import java.util.List;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.RoomDto;
import com.example.movie.dto.SeatDto;
import com.example.movie.dto.TestDto;


public interface BusinessMapper  extends MybatisMapper {
	
	// 사업자 회원가입 아이디 중복체크
	public int buIdCheck(String bid);
	
	// 사업자 회원 가입
	public void businessInsert(BusinessDto business);

	// 사업자 로그인
	public String getb_pw(String b_id);

	// 사업자 검색(세션 저장용?)
	public BusinessDto getBusiness(String b_id);
	
	// test insertMovie
	public void insertMovie(TestDto tDto);
	//상영관 목록 가져오기
	public List<RoomDto> getRoomList();
	//상영관 삭제하기
	public boolean RoomDelete(Integer roomseq);
	//상영관 등록하기
	public void roomInsert(RoomDto roDto);
	//좌석정보 등록하기
	public void seatInsert(SeatDto seDto);
}
