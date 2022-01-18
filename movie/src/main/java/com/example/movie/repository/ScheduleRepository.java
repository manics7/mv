package com.example.movie.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.Schedule;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

	List<Schedule> findBySchDateBetween(Date startDate, Date endDate);

	List<Schedule> findByThCodeAndSchDateBetween(Integer thCode, Date startDate, Date endDate);
	
	List<Schedule> findByThCodeAndSchDate(Integer thCode, Date schDate);

	List<Schedule> findByMovieCdAndSchDate(String movieCd, Date schDate);

	Schedule findByMovieCdAndThCodeAndRoomNoAndSchDate(String movieCd, Integer thCode, Integer roomNo, Date schDate);
}
