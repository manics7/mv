package com.example.movie.mapper;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.BusinessDto;
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
	
	//사업자가 등록한 극장이름
	public String selectThNameByBid(String b_id);
	
}
