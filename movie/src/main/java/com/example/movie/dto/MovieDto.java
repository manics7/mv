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
	private Timestamp open_dt;
	private int genre_nm;
	private String directors;
	private String actors;
	private int show_types;
	private int watch_grade_nm;

	private int state;
}
