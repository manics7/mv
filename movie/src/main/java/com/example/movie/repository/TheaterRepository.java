package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.dto.MovieOfficial;
import com.example.movie.dto.Question;
import com.example.movie.dto.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {

	
}
