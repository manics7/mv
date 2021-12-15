package com.example.movie.mapper;

import java.util.List;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.Emp;

public interface EmpMapper  extends MybatisMapper {
	List<Emp> empList() throws Exception;
	
	Emp emp() throws Exception;
}
