package com.example.movie.mapper;

import java.util.List;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.MvOfficialDto;
import com.example.movie.dto.ReviewMovieDto;

public interface AdminMapper  extends MybatisMapper {

	// 현재상영작 불러오기
	public List<MvOfficialDto> getMovieList();

	// 영화 상세 페이지 이동
	public MvOfficialDto movieDetail(String movie_cd);

	// 관람평 목록 가져오기
	public List<ReviewMovieDto> reviewMovie(String movie_cd);
	
}
