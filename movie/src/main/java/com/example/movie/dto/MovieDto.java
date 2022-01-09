package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MovieDto {
	
	private int mv_seq;
	private int th_code;
	private String movie_cd;
	private String movie_nm;
	private int show_tm;
	private String open_dt; // String or Timestamp 확인좀~ 까먹지 말고~ 제발
	private String genre_nm;
	private String directors;
	private String actors;
	private String show_types;
	private String watch_grade_nm;
	private String poster;
	
	private int state;

}
