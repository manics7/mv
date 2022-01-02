package com.example.movie.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.movie.entity.QReservation;
import com.example.movie.entity.QReservationSeat;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	private QReservation reservation = new QReservation("rsrv");
	private QReservationSeat reservationSeat = new QReservationSeat("rsrvSeat");
	
	public Integer rsrvSeatCnt(Integer schCode, Integer schDetailSeq) {
		
	Long rsrvSeatCnt =	jpaQueryFactory
				.select(reservation.count())
				.from(reservation)
				.join(reservationSeat)
				.on(reservation.rsrvNo.eq(reservationSeat.rsrvNo))
				.where(reservation.schCode.eq(schCode).and(reservation.schDetailSeq.eq(schDetailSeq)))
				.fetchFirst();
				
				return rsrvSeatCnt.intValue();
	}

}
