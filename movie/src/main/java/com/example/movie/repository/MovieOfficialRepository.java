package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.MovieOfficial;
import com.example.movie.entity.Question;

@Repository
public interface MovieOfficialRepository extends JpaRepository<MovieOfficial, String> {

	
}
