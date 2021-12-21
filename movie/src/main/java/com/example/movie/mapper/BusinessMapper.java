package com.example.movie.mapper;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.BusinessDto;


public interface BusinessMapper  extends MybatisMapper {
	
	// 사용자 회원 가입
	public void businessInsert(BusinessDto business);
}
