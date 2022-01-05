package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MovieOfficialDto {
	
	private String movie_cd;
	private String movie_nm;
	private String movie_content;
	private int show_tm;
	private Timestamp open_dt;
	private String genre_nm;
	private String directors;
	private String actors;
	private String show_types;
	private String poster;
	private String stillcut1;
	private String stillcut2;
	private String stillcut3;
	private String stillcut4;
	private String stillcut5;

}
