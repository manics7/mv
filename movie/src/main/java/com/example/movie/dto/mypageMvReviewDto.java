package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;
@Data
public class mypageMvReviewDto {

	private int mv_review_num;
	private String mv_review_id;
	private String mv_review_of_mv_num;
	private int mv_review_score;
	private String mv_review_comment;
	private Timestamp mv_review_date;
	
	
	
	private String mv_name;

}
