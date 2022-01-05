package com.example.movie.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "MOVIE_OFFICIAL")
@NoArgsConstructor // 기본생성자 생성
@ToString // toString() 함수 자동생성
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MovieOfficial {

	@Id	
	@Column(name="MOVIE_CD", columnDefinition="영화코드")
	private String movieCd;
	
	@Column(name="MOVIE_NM", columnDefinition="영화제목")
	private String movieNm;
	
	@Column(name="MOVIE_CONTENT", columnDefinition="줄거리")
	private String movieContent;
	
	@Column(name="SHOW_TM", columnDefinition="상영시간")
	private Integer showTm;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="OPEN_DT", columnDefinition="개봉연도")
	private Date openDt;
	
	@Column(name="GENRE_NM", columnDefinition="장르")
	private String genreNm;
	
	@Column(name="DIRECTORS", columnDefinition="감독")
	private String directors;
	
	@Column(name="ACTORS", columnDefinition="배우")
	private String actors;
	
	@Column(name="SHOW_TYPES", columnDefinition="상영형태")
	private String showTypes;
	
	@Column(name="WATCH_GRADE_NM", columnDefinition="관람등급")
	private String watchGradeNm;
	
	@Column(name="POSTER", columnDefinition="포스터")
	private String poster;
	
	@Column(name="STILLCUT1", columnDefinition="스틸컷")
	private String sttilcut1;
	
	@Column(name="STILLCUT2", columnDefinition="스틸컷")
	private String sttilcut2;
	
	@Column(name="STILLCUT3", columnDefinition="스틸컷")
	private String sttilcut3;
	
	@Column(name="STILLCUT4", columnDefinition="스틸컷")
	private String sttilcut4;
	
	@Column(name="STILLCUT5", columnDefinition="스틸컷")
	private String sttilcut5;
	
}
