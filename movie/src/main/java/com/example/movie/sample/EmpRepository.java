package com.example.movie.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.movie.dto.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer> {

	public void aaa();
	
}
