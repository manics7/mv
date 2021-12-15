package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.movie.dto.Emp;
import com.example.movie.dto.Test;

public interface Test1Repository extends JpaRepository<Test, Integer> {

}
