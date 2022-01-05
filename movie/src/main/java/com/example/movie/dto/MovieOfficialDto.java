package com.example.movie.dto;


import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MovieOfficialDto {

	private String movie_Cd;
	private String movie_Nm;
	private String movie_Content;
	private Integer show_Tm;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date open_Dt;
	private String genre_Nm;
	private String directors;
	private String actors;
	private String show_Types;
	private String watch_Grade_Nm;
	private String poster;
	private String stillcut1;
	private String stillcut2;
	private String stillcut3;
	private String stillcut4;
	private String stillcut5;
	
}
