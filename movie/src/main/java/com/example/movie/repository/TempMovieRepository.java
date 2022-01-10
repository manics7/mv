package com.example.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.MovieOfficial;
import com.example.movie.entity.TempMovie;

@Repository
public interface TempMovieRepository extends JpaRepository<TempMovie, String> {

	List<TempMovie> findByOpenDtLessThanEqual(String date);
	
	
}