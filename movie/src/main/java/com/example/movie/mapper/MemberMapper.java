package com.example.movie.mapper;

import java.util.List;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.MemberDto;
import com.example.movie.dto.QuestionDto;
import com.example.movie.dto.mvReviewDto;
import com.example.movie.dto.paymentDto;
import com.example.movie.dto.reservationDto;


public interface MemberMapper  extends MybatisMapper {
	
	//id로 비밀번호찾기
	public String pwdSelect(String id);
	//id로 회원정보 불러오기
	public MemberDto memberSelect(String id);
	
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
	public paymentDto selectPaymentByRsrvno(String rsrvno);
	//취소한 결제내역 검색
	public String selectPaymentCancel(String tid);
	
	//예매번호로 스케줄번호 검색
	public String selectSchno (String rsno);
	//스케줄번호로 극장코드검색
	public String selectThcode (String schno);
	//극장코드로 극장이름검색
	public String selectThname (String thcode);
	//스케줄번호로 영화코드 검색
	public String selectMoviecode (String schno);
	
}
