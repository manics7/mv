package com.example.movie.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.MemberDto;

public interface MemberMapper  extends MybatisMapper {
	//회원정보 목록 가져오기
	List<MemberDto> getList(Map<String, Integer> mmap);
	//전체 글 개수 구하기
	int getBoardCnt();
	//회원 삭제 쿼리 
	void deleteMember(String m_id);
	//회원 검색 쿼리
	List<MemberDto> selectMember(String m_id);
	
	void getmboardSelect(String m_id);
	int getsearchBoardCnt(String m_id);
	List<thdetailDto> thdetailInsert(thdetailDto thdto);
	
	
	// 이용자 회원가입 아이디 중복체크
	public int idCheck(String mid);
	
	// 이용자 회원 가입
	public void memberInsert(MemberDto member);

	// 이용자 로그인
	public String getPw(String m_id);
	
	// 이용자 검색(세션 저장용?)
	public MemberDto getMember(String m_id);
	
}
