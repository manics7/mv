package com.example.movie.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.dto.Schedule;

@Repository
public interface ScheduleRepositoy extends JpaRepository<Schedule, Integer> {

	List<Schedule> findBySchDateBetween(Date startDate, Date endDate);
	
}
