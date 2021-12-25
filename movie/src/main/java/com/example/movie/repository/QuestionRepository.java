package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.movie.dto.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	
}
