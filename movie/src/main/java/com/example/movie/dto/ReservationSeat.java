package com.example.movie.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@Entity
@Table(name = "RESERVATION_SEAT")
@NoArgsConstructor // 기본생성자 생성
@ToString // toString() 함수 자동생성
@IdClass(ReservationSeatId.class)
public class ReservationSeat {

	@Id
	@Column(name="RSRV_NO", columnDefinition="예매번호")
	private String rsrvNo;
	
	@Id
	@Column(name="SEAT_NO", columnDefinition="좌석번호")
	private Integer seatNo;
	
}
