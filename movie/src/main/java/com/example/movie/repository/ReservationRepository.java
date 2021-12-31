package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.dto.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	
}
