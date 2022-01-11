package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class reportMvReviewDto {
	private int rp_mv_seq;
	private int movie_review;
	private String rp_m_id;
	private String rpt_m_id;
	private String rp_why;
	private Timestamp rp_date;
	private String rp_contents;
	private int rp_state;
	
	
}
