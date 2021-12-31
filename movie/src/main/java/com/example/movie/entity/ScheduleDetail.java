package com.example.movie.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name="SCH_DETAIL_START", columnDefinition="시작시간")
	@Temporal(TemporalType.TIMESTAMP)
	private Date schDetailStart;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name="SCH_DETAIL_END", columnDefinition="종료시간")
	@Temporal(TemporalType.TIMESTAMP)
	private Date schDetailEnd;

}
