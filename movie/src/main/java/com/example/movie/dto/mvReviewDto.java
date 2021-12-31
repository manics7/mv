package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class mvReviewDto {
	private int mv_review;
	private String mv_review_id;
	private String mv_review_moviecd;
	private int mv_review_score;
	private String mv_review_comment;
	private Timestamp mv_review_date;
	
	private String mvName;
}
