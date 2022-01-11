package com.example.movie.dto;


import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MovieOfficialDto {

	private String movie_cd;
	private String movie_nm;
	private String movie_content;
	private Integer show_tm;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@Temporal(TemporalType.DATE)
	private Date open_dt;
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
