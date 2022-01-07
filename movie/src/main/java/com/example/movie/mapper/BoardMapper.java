package com.example.movie.mapper;

import java.util.List;
import java.util.Map;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.BoardDto;
import com.example.movie.dto.ReplyDto;
import com.example.movie.dto.TheaterDto;

public interface BoardMapper extends MybatisMapper {
	//후기 게시글 목록 가져오기
	public List<BoardDto> getRvList(Map<String, Integer> pmap);
	//전체 게시글 수 구하기
	public int getrvBoardCnt();
	//검색한 게시글 수 구하기
	public int getrvSearchCnt(BoardDto bDto);
	//검색으로 게시글 목록 가져오기
	public List<BoardDto> selectSearchList(BoardDto bDto);
	//영화관 이름 목록 가져오기
	public List<TheaterDto> getTHList();
	//게시글 작성하기
	public void rvBoardInsert(BoardDto bDto);
	//게시글 본문 가져오기
	public BoardDto getRvContent(Integer rnum);
	//조회수 업데이트
	public void viewUpdate(Integer rnum);
	//게시글 수정하기
	public boolean RvUpdate(BoardDto bDto);
	//게시글 삭제하기
	public boolean RvboardDelete(Integer rnum);
	//댓글 작성하기
	public void replyInsert(ReplyDto reDto);
	//댓글 목록 출력
	public List<ReplyDto> getReList(Integer rnum);
	//신고 처리하기
	public void reportRvInsert();
}
