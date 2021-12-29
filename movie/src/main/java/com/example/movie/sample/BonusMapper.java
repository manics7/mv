package com.example.movie.sample;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.movie.config.MybatisMapper;

public interface BonusMapper  extends MybatisMapper {
	@Select("SELECT * FROM BONUS")
	List<BonusDto> getList() throws Exception;
	
	void insertBonus(BonusDto bonusDto) throws Exception;
}
