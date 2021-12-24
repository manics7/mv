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
@Table(name = "MOVIE_OFFICIAL")
@NoArgsConstructor // 기본생성자 생성
@ToString // toString() 함수 자동생성
public class MovieOfficial {

	@Id
	@Column(name="MOVIECD", columnDefinition="영화코드")
	private String movieCd;
	
	@Column(name="MOVIENM", columnDefinition="영화제목")
	private String movieNm;
	
	@Column(name="PRDTYEAR", columnDefinition="제작연도")
	private String prdtYear;
	
	@Column(name="SHOWTM", columnDefinition="상영시간")
	private Integer showTm;
	
	@Column(name="OPENDT", columnDefinition="개봉연도")
	private String openDt;
	
	@Column(name="GENRENM", columnDefinition="장르")
	private String genreNm;
	
	//@Column(name="DIRECTORS", columnDefinition="감독")
	//private String directors;
	
}
