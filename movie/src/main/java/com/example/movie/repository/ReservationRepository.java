package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.movie.dto.Question;
import com.example.movie.dto.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	
}
