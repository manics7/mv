package com.example.movie.sample;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Emp, Integer> {

	public void aaa();
	
}
