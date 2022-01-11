package com.example.movie.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@Entity
@Table(name = "RESERVATION_SEAT")
@NoArgsConstructor // 기본생성자 생성
@ToString // toString() 함수 자동생성
//@IdClass(ReservationSeatId.class)
@SequenceGenerator (	name = "RSRV_SEAT_SEQ_GENERATOR"
	,  sequenceName = "RSRV_SEAT_SEQ",  initialValue = 1, allocationSize = 1)	//매핑할 데이터 베이스 스퀀스 이름)
public class ReservationSeat {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RSRV_SEAT_SEQ_GENERATOR")
	@Column(name="RSRV_SEAT_SEQ", columnDefinition="예매좌석키")
	private Integer rsrvSeatSeq;
	
	//@Id
	@Column(name="RSRV_NO", columnDefinition="예매번호")
	private Integer rsrvNo;
	
	//@Id
	@Column(name="SEAT_NO", columnDefinition="좌석번호")
	private String seatNo;
	
}
