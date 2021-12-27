package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class reportMvReviewDto {
	private int rpnum;
	private int mvrnum;
	private String rpid;
	private String rptid;
	private String rpwhy;
	private Timestamp rpdate;
	private int state;
	
	private String contents;
}
