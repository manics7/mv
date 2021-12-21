package com.example.movie.mapper;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.MemberDto;

public interface MemberMapper  extends MybatisMapper {
	
	// 이용자 회원 가입
	public void memberInsert(MemberDto member);

	// 이용자 로그인
	public String getPw(String m_id);
	
}
