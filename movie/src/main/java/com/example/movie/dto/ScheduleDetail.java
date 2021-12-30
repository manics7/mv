package com.example.movie.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ScheduleDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="SCH_DETAIL_SEQ", columnDefinition="상영 시간 디테일 키")
	private Integer schDetailSeq;
	
	@Column(name="SCH_CODE", columnDefinition="상영 시간 키")
	private Integer schCode;
	
	@Column(name="SCH_DETAIL_START", columnDefinition="시작시간")
	private String schDetailStart;

	@Column(name="SCH_DETAIL_END", columnDefinition="종료시간")
	private String schDetailEnd;

}
