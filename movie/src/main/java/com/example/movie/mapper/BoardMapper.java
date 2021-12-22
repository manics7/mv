package com.example.movie.mapper;

import java.util.List;
import java.util.Map;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.BoardDto;
import com.example.movie.dto.TheaterDto;

public interface BoardMapper extends MybatisMapper {
	//후기 게시글 목록 가져오기
	public List<BoardDto> getRvList(Map<String, Integer> pmap);
	//전체 게시글 수 구하기
	public int getrvBoardCnt();
	//검색으로 게시글 목록 가져오기
	public List<BoardDto> selectSearchList(BoardDto bDto);
	//게시글 작성하기
	public void rvBoardInsert(BoardDto bDto);
	//영화관 이름 목록 가져오기
	public List<TheaterDto> getTHList();
}
