package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardDto {
	private int rnum;
	private int thcode;
	private String mid;
	private String rtitle;
	private String rcontent;
	private int rview;
	private int rlike;
	private Timestamp rdate;
	
	private String thname;
	
	//검색 필터
	private String type; //검색 타입
	private String keyword; //검색 내용
	
}