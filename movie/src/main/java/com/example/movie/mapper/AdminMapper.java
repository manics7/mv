package com.example.movie.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.MovieDto;
import com.example.movie.dto.MovieOfficialDto;
import com.example.movie.dto.ReviewMovieDto;
import com.example.movie.dto.quesReplyDto;
import com.example.movie.dto.quesboardDto;
import com.example.movie.dto.reportMvReviewDto;

public interface AdminMapper extends MybatisMapper {

	// 현재상영작 불러오기
	public List<MovieOfficialDto> getMovieList();

	// 영화 상세 페이지 이동
	public MovieOfficialDto movieDetail(String movie_cd);

	// 관람평 목록 가져오기
	public List<ReviewMovieDto> reviewMovie(String movie_cd);

	//회원 정보 출력 에서 회원 작성 글 선택 시 출력 되는 해당 회원의 1대1문의게시글 항목 가져오기
	List<quesboardDto> getquesboardSelect(String m_id);

	//1대1문의게시판 읽기 
	List<quesboardDto> getquesboardRead(int ques_no);
	
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
	
	public List<reportMvReviewDto> selectReportMvReviewSort(Map<String, Integer> pmap);
	//사업자 회원 목록 출력
	public List<BusinessDto> getbuslist(HashMap<String, Integer> busmap);
	//사업자 회원 목록 페이징시 필요한 목록 수
	public int getBusCnt();


}
