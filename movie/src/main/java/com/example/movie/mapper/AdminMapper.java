package com.example.movie.mapper;

import java.util.List;
import java.util.Map;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.reportMvReviewDto;
import com.example.movie.dto.quesReplyDto;
import com.example.movie.dto.quesboardDto;

import com.example.movie.config.MybatisMapper;

public interface AdminMapper extends MybatisMapper {

	List<quesboardDto> getQuesList(Map<String, Integer> qmap);

	int getBoardCnt();

	List<quesboardDto> getboardSelect(String m_id);

	List<quesboardDto> getboardRead(int ques_no);
	
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
	
	public List<reportMvReviewDto> selectReportMvReviewSort(Map<String, Integer> pmap);
	
	public List<reportMvReviewDto> selectReportMvReviewSortDesc(Map<String, Integer> pmap);
	
}
