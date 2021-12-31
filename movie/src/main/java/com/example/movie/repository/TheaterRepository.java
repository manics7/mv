package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.MovieOfficial;
import com.example.movie.entity.Question;
import com.example.movie.entity.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {

	
}
