package com.example.movie.mapper;

import java.util.List;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.MvOfficialDto;

public interface AdminMapper  extends MybatisMapper {

	// 현재상영작 불러오기
	public List<MvOfficialDto> getMovieList();
	
}
