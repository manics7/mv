package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.dto.Movie;
import com.example.movie.dto.MovieOfficial;
import com.example.movie.dto.Question;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	
}
