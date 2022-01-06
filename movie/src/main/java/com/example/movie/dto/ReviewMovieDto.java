package com.example.movie.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReviewMovieDto {
	private int mv_review;
	private String mv_review_id;
	private String mv_review_moviecd;
	private int mv_review_score;
	private String mv_review_comment;
	@JsonFormat(pattern = "MM-dd HH:mm", timezone = "Asia/Seoul")
	private Timestamp mv_review_date;
	
}
