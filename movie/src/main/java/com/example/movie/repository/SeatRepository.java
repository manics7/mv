package com.example.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

	List<Seat> findByThCodeAndRoomNo(Integer thCode, Integer roomNo);

}
