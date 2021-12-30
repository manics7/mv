package com.example.movie.dto;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@DynamicInsert // null 컬럼 입력 제외
@DynamicUpdate  // null 컬럼 업데이트 제외
@Entity
@NoArgsConstructor // 기본생성자 생성
@ToString // toString() 함수 자동생성
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Schedule {

	@Id
	@Column(name="SCH_CODE", columnDefinition="상영 시간 키")
	private String schCode;
	
	@Column(name="TH_CODE", columnDefinition="영화코드")
	private Integer thCode;
	
	@Column(name="ROOM_NO", columnDefinition="상영관코드")
	private Integer roomNo;
	
	@JsonIgnore
	@Column(name="MOVIE_CD", columnDefinition="영화코드")
	private String movieCd;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd")	
	@Column(name="SCH_DATE", columnDefinition="상영날짜")
	@Temporal(TemporalType.DATE)
	private Date schDate;
	
	@Column(name="SCH_TIME", columnDefinition="대기 시간")
	private Integer schTime;
	
	//@OneToOne(fetch = FetchType.LAZY) 
	@OneToOne(fetch = FetchType.LAZY)   
	@JoinColumn(name = "MOVIE_CD", referencedColumnName = "MOVIE_CD"
						, insertable = false, updatable = false, nullable = false)
	private MovieOfficial movieOfficial;
	
	@OneToOne(fetch = FetchType.LAZY)   
	@JoinColumn(name = "TH_CODE", referencedColumnName = "TH_CODE"
						, insertable = false, updatable = false, nullable = false)
	private Theater theater;
	
}
