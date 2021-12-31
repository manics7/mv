package com.example.movie.mapper;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.MemberDto;


import java.util.List;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.MemberDto;
import com.example.movie.dto.QuestionDto;
import com.example.movie.dto.mvReviewDto;
import com.example.movie.dto.paymentDto;
import com.example.movie.dto.reservationDto;


public interface MemberMapper  extends MybatisMapper {
	
	//id로 작성한 문의사항 리스트 
	public List<QuestionDto> selectQuestion(String id);
	//id로 작성한 영화리뷰 리스트
	public List<mvReviewDto> selectpmvReview(String id);
	//id에맞는 예매내역 리스트
	public List<reservationDto> selectRsrvByid(String id);
	
	// id로 작성한 문의사항 글 갯수
	public int selectQuestionCnt(String id);
	// id로 작성한 영화리뷰 갯수
	public int selectpmvReviewCnt(String id);
	
	//영화코드로 영화이름 검색;
	public String selectMovieName(String mvcd);
	//에매번호로 결제내역 검색
	public paymentDto selectPaymentByRsrvno(int rsrvno);
	//취소한 결제내역 검색
	public String selectPaymentCancel(String tid);
	
	//예매번호로 스케줄번호 검색
	public int selectSchno (String rsno);
	//스케줄번호로 극장코드검색
	public int selectThcode (int schno);
	//극장코드로 극장이름검색
	public String selectThname (int thcode);
	//스케줄번호로 영화코드 검색
	public String selectMoviecode (int schno);
	//영화리뷰 삭제
	public boolean delMvReview(int mvrnum);
	


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
	
	// 이용자 회원가입 아이디 중복체크
	public int idCheck(String mid);
	
	// 이용자 회원 가입
	public void memberInsert(MemberDto member);

	// 이용자 로그인
	public String getPw(String m_id);
	
	// 이용자 검색(세션 저장용?)
	public MemberDto getMember(String m_id);
}