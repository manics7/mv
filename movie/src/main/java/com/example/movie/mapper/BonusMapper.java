package com.example.movie.mapper;

import java.util.List;

import com.example.movie.config.MybatisMapper;

public interface BonusMapper  extends MybatisMapper {
	List<BonusDto> getList() throws Exception;
	
	void insertBonus(BonusDto bonusDto) throws Exception;
}
