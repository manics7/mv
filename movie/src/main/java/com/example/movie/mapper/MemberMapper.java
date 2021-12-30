package com.example.movie.mapper;

import org.springframework.stereotype.Repository;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.Member;

@Repository
public interface MemberMapper  extends MybatisMapper {
	
	// 이용자 회원가입 아이디 중복체크
	public int idCheck(String mId);
	
	// 이용자 회원 가입
	public void memberInsert(Member member);

	// 이용자 로그인
	public String getPw(String mId);
	
	// 이용자 검색(세션 저장용?)
	public Member getMember(String m_id);
	
}
