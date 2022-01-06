package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.ScheduleDetail;

@Repository
public interface ScheduleDetailRepositoy extends JpaRepository<ScheduleDetail, Integer> {

}
