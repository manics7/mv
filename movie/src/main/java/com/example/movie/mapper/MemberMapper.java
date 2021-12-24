package com.example.movie.mapper;

import java.util.List;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.MemberDto;
import com.example.movie.dto.QuestionDto;
import com.example.movie.dto.mvReviewDto;
import com.example.movie.dto.paymentCancelDto;
import com.example.movie.dto.paymentDto;
import com.example.movie.dto.reservationDto;


public interface MemberMapper  extends MybatisMapper {
	
	//id로 비민번호찾기
	public String pwdSelect(String id);
	//id로 회원정보 불러오기
	public MemberDto memberSelect(String id);
	
	//id로 작성한 문의사항 리스트 
	public List<QuestionDto> selectQuestion(String id);
	//id로 작성한 영화리뷰 리스트
	public List<mvReviewDto> selectpmvReview(String id);
	// id로 작성한 문의사항 글 갯수
	public int selectQuestionCnt(String id);
	// id로 작성한 영화리뷰 갯수
	public int selectpmvReviewCnt(String id);
	//영화코드로 영화이름 검색;
	public String selectMovieName(String mvcd);
	
	//결제제내역 리스트
	public List <paymentDto> selectPayment();
	//취소한 결제내역 리스트
	public List <paymentCancelDto> selectPaymentCancel();
	
	
	//미완
	public String selectSchno (String rsno);
	public String selectThcode (String schno);
	public String selectThname (String thcode);
	public String selectMoviecode (String schno);
	
}
