package com.example.movie.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.MovieDto;
import com.example.movie.dto.MovieOfficialDto;
import com.example.movie.dto.ReportReplyDto;
import com.example.movie.dto.ReportReviewDto;
import com.example.movie.dto.ReviewMovieDto;
import com.example.movie.dto.quesReplyDto;
import com.example.movie.dto.quesboardDto;
import com.example.movie.dto.reportMvReviewDto;

public interface AdminMapper extends MybatisMapper {

	//회원 정보 출력 에서 회원 작성 글 선택 시 출력 되는 해당 회원의 1대1문의게시글 항목 가져오기
	List<quesboardDto> getquesboardSelect(String m_id);

	//1대1문의게시판 읽기 
	quesboardDto getquesboardRead(int ques_no);
	
	//1대1문의사항 게시판 목록 가져오기
	public List<quesboardDto> getQuesList(Map<String, Integer> qmap);
	
	//1대1문의사항 게시판 작성 글 수
	public int getquesBoardCnt();
	
	//문의사항 답변 불러오기
	public quesReplyDto selectQuesReply(int ques_num);
	
	//신고당한 영화리뷰의 갯수
	public int selectReportMvReviewCnt();
	
	//신고당한 영화리뷰들 리스트
	public List<reportMvReviewDto> selectReportMvReview(Map<String, Integer> pmap); 
	
	//리뷰번호로 영화리뷰내용 불러오기
	public String selectMvReviewByReviewNum(int mvrNum);
	//리뷰번호로 작성자id 받아오기
	public String selectIdFromMvReview(int mvrNum);
	
	//리뷰번호로 리뷰 삭제
	public boolean delMvReview(int mvrNum);
	//받아온 id의 경고 증가
	public boolean updateWarning(String id);
	//영화리뷰 신고처리 완료후 상태 변경
	public boolean updateRpMvReviewState(int mvrNum);
	
	//신고된 리뷰 신고 테이블에서 삭제
	public boolean delMvReviewReport(int mvrNum);
	
	//사업자가 요청한 영화 목록
	public List<MovieDto> selectMovieRequest();
	//관리자가 등록한 영화 목록
	public List<MovieOfficialDto> selectMvOfficial();
	
	//사업자 회원 목록 출력
	public List<BusinessDto> getbuslist(HashMap<String, Integer> busmap);
	//사업자 회원 목록 페이징시 필요한 목록 수
	public int getBusCnt();
	//영화시퀀스번호로 사업자 영화가져옴
	public MovieDto selectMovieBySeq(int mv_seq);
	//관리자 영화등록
	public void adminMovieInsert(MovieOfficialDto mvofficialDto);

	public ModelAndView quesboard_replywrite(quesReplyDto qrdto);
	//신고된 영화관리뷰 게시글 갯수
	public int selectReportReviewCnt();
	//신고된 댓글 갯수
	public int selectReportReplyCnt();
	//신고 영화관리뷰 리스트
	public List<ReportReviewDto> selectReportReview(Map<String, Integer> pmap);
	//신고 댓글 리스트
	public List<ReportReplyDto> selectReportReply(Map<String, Integer> pmap);

	//1대1 문의사항의 답변 처리.(관리자 입장)
	public void insertReplyWrite(quesReplyDto qrdto);

	public void selectReplyQues();
	//public quesReplyDto insertReplyWrite(quesReplyDto qrdto);
	//무비오피셜에 등록된 영화 있는지 검색(중복확인)
	public int selectMvOfficialCntByMovieCode(String movieCd);
	//무비오피셜 수정(관리자 영화등록 수정)
	public void adminMovieUpdate(MovieOfficialDto mvofficialDto);



}
