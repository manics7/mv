package com.example.movie.dto;

import lombok.Data;

@Data
public class MvOfficialDto {
	
	private String movie_cd;
	private String movie_nm;
	private String movie_content;
	private int show_tm;
	private String open_dt; // 나중에 타임스탬프로 바꿔야 함?
	private String genre_nm;
	private String directors;
	private String actors;
	private String show_types;
	private String watch_grade_nm;
	private String poster;
	private String stillcut1;
	private String stillcut2;
	private String stillcut3;
	private String stillcut4;
	private String stillcut5;
}
