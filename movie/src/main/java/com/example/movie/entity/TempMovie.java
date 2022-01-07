package com.example.movie.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "TEMP")
@NoArgsConstructor // 기본생성자 생성
@ToString // toString() 함수 자동생성
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TempMovie {

	@Id	
	@Column(name="moviecd", columnDefinition="영화코드")
	private String movieCd;
	
	@Column(name="movienm", columnDefinition="영화제목")
	private String movieNm;
	
	@Column(name="opendt", columnDefinition="개봉일")
	private String openDt;
	
	@Column(name="repgenrenm", columnDefinition="대표 장르")
	private String repGenreNm;
	
	@Column(name="genreAlt", columnDefinition="장르 전체")
	private String genreAlt;
	
}
