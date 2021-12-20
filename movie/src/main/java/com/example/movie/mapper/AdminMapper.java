package com.example.movie.mapper;

import java.util.List;
import java.util.Map;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.quesboardDto;

public interface AdminMapper  extends MybatisMapper {

	List<quesboardDto> getQuesList(Map<String, Integer> qmap);

	int getBoardCnt();
	
}
