package com.example.movie.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	List<Reservation> findBySchCodeAndSchDetailSeq(int schCode, int schDetailSeq);

	@Query("SELECT MAX(rsrvNo)  FROM Reservation")
	Integer lastRsrvNo();
	
}
