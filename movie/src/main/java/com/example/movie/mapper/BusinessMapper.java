package com.example.movie.mapper;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.BusinessDto;


public interface BusinessMapper  extends MybatisMapper {
	
	// 사업자 회원 가입
	public void businessInsert(BusinessDto business);

	// 사업자 로그인
	public String getb_pw(String b_id);

	// 사업자 검색(세션 저장용?)
	public BusinessDto getBusiness(String b_id);
}
