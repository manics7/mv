package com.example.movie.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "SCHEDULE_DETAIL")
@NoArgsConstructor // 기본생성자 생성
@ToString // toString() 함수 자동생성
public class ScheduleDetail {

	@Id
	@Column(name="SCHDETAIL_SEQ", columnDefinition="상영 시간 키")
	private String schDetailSeq;
	
	@Column(name="SCH_CODE", columnDefinition="영화코드")
	private String schCode;
	
	@Column(name="SCH_DETAIL_START", columnDefinition="영화관코드")
	private String schDetailStart;

	@Column(name="SCH_DETAIL_END", columnDefinition="상영관코드")
	private String schDetailEnd;

}
