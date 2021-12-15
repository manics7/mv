package com.example.movie.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EmpRepository extends JpaRepository<Emp, Integer> {

	
}
