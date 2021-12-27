package com.example.movie.mapper;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.TheaterDto;

public interface BusinessMapper  extends MybatisMapper {
	//영화관 등록
	public void theaterAdd(TheaterDto theater);
}
