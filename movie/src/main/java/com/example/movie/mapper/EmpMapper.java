package com.example.movie.mapper;

import java.util.List;

import com.example.movie.config.MybatisMapper;

public interface EmpMapper  extends MybatisMapper {
	List<Emp> empList() throws Exception;
	
	Emp emp() throws Exception;
}
