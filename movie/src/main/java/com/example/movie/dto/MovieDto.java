package com.example.movie.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MovieDto {
	
	private int mv_seq;
	private int th_code;
	private String movie_cd;
	private String movie_nm;
	private int show_tm;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@Temporal(TemporalType.DATE)
	private Date open_dt; // String or Timestamp 확인좀~ 까먹지 말고~ 제발
	private String genre_nm;
	private String directors;
	private String actors;
	private String show_types;
	private String watch_grade_nm;
	private String poster;
	
	private int state;

}
