package com.example.movie.mapper;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.memberDto;

public interface MemberMapper  extends MybatisMapper {
	
	// 이용자 회원 가입
	public void memberInsert(memberDto member);
	
}
