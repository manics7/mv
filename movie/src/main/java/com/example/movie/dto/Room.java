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
public class Room {
	
	@Id
	@Column(name="ROOM_NO", columnDefinition="상영관 번호")
	private String bId;
	
	@Column(name="TH_CODE", columnDefinition="영화관코드")
	private Integer thCode;

	@Column(name="ROOM_CLASS", columnDefinition="상영관 종류")
	private String roomClass;
	
	@Column(name="ROOM_NAME", columnDefinition="상영관 이름")
	private String roomName;
	
	@Column(name="ROOM_ROW", columnDefinition="전체 행")
	private Integer roomRow;
	
	@Column(name="ROOM_COL", columnDefinition="전체 열")
	private Integer roomCol;	
	
	@Column(name="ROOM_STATUS", columnDefinition="총 좌석수")
	private String roomStatus;	
	
	
}
