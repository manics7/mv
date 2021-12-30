package com.example.movie.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@Table(name = "MOVIE")
@NoArgsConstructor // 기본생성자 생성
@ToString // toString() 함수 자동생성
@SequenceGenerator (
	name = "[만들 시퀀스 이름]MOVIE_SEQ_GENERATOR",
    sequenceName = "[DB에 만든 시퀀스 이름]MOVIE_SEQ",
    initialValue = 1, allocationSize = 1)	//매핑할 데이터 베이스 스퀀스 이름)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movie {


		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOVIE_SEQ_GENERATOR")
//		@SequenceGenerator(sequenceName = "MOVIE_SEQ_GENERATOR", allocationSize = 1, name = "MOVIE_SEQ")
		@Column(name="MV_SEQ", columnDefinition = "영화 시퀀스")
		private Integer mvSeq;
		
		@Column(name = "TH_CODE", columnDefinition = "영화관 코드")
		private Integer thCode;
		
		@Column(name="MOVIE_CD", columnDefinition="영화코드")
		private String movieCd;
		
		@Column(name="MOVIE_NM", columnDefinition="영화제목")
		private String movieNm;
		
		@Column(name="SHOW_TM", columnDefinition="상영시간")
		private Integer showTm;
		
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
		@DateTimeFormat(pattern="yyyy-MM-dd") // 2012-02-02 20120202
		@Temporal(TemporalType.DATE)
		@Column(name="OPEN_DT", columnDefinition="개봉연도")
		private Date openDt;
		
		@Column(name="GENRE_NM", columnDefinition="장르")
		private String genreNm;
		
		@Column(name="DIRECTORS", columnDefinition="감독")
		private String directors;
		
		@Column(name = "ACTORS", columnDefinition = "배우")
		private String actors;
		
		@Column(name = "SHOW_TYPES", columnDefinition = "상영 형태, 2d 3d")
		private Integer showTypes;
		
		@Column(name = "WATCH_GRADE_NM", columnDefinition = "관람 등급")
		private Integer watchGradeNm;
		
	}
