package com.example.movie.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Seat {
	
	@Id
	@Column(name="ROOM_NO", columnDefinition="사업자, ID")
	private String bId;
	
	@Column(name="TH_CODE", columnDefinition="영화관코드")
	private Integer thCode;

	@Column(name="SEAT_NO", columnDefinition="영화관이름")
	private String seatNo;
	
	@Column(name="SEAT_CLASS", columnDefinition="로고 이미지")
	private String seatClass;
	
	@Column(name="SEAT_ROW", columnDefinition="위치정보")
	private String seatRow;
	
	@Column(name="SEAT_COL", columnDefinition="영화관소개")
	private String seatCol;	
	
	@Column(name="SEAT_STATUS", columnDefinition="장르")
	private String seatStatus;	
	
	
}
