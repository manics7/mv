package com.example.movie.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.ScheduleDetail;


@Repository
public interface ScheduleDetailRepository extends JpaRepository<ScheduleDetail, Integer>  {

	List<ScheduleDetail> findBySchCode(Integer schCode);

	
}
