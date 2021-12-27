package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.dto.Schedule;

@Repository
public interface ScheduleRepositoy extends JpaRepository<Schedule, String> {

	
}
