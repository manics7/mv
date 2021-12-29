package com.example.movie.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@DynamicInsert // null 컬럼 입력 제외
@DynamicUpdate  // null 컬럼 업데이트 제외
@Entity
@NoArgsConstructor // 기본생성자 생성
@ToString // toString() 함수 자동생성
//@IdClass(value = SeatId.class)
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="SEAT_SEQ", columnDefinition="좌석 시퀀스")
	private Integer seatSeq;
	
	//@Id
	@Column(name="TH_CODE", columnDefinition="영화관코드")
	private Integer thCode;
	
	//@Id
	@Column(name="ROOM_NO", columnDefinition="상영관코드")
	private Integer roomNo;
	
	//@Id
	@Column(name="SEAT_NO", columnDefinition="좌석번호")
	private String seatNo;
	
	@Column(name="SEAT_CLASS", columnDefinition="상영관 종류")
	private String seatClass;
	
	@Column(name="SEAT_ROW", columnDefinition="행")
	private Integer seatRow;
	
	@Column(name="SEAT_COL", columnDefinition="열")
	private Integer seatCol;	
	
	@Column(name="SEAT_STATUS", columnDefinition="상태(벽,통로,좌석)")
	private Integer seatStatus;	
	
	
}
