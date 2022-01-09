package com.example.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.MovieOfficial;
import com.example.movie.entity.TempMovie;

@Repository
public interface TempMovieRepository extends JpaRepository<TempMovie, String> {

	// 영화 목록 출력(입력 일자보다 느린)
	List<TempMovie> findByOpenDtLessThanEqual(String date);

}
