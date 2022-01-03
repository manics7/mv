package com.example.movie.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReplyDto {
	private int renum;
	private int rnum;
	private String mid;
	private String recontent;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",
			timezone = "Asia/Seoul")
	private Timestamp redate;
	private int relike;
}
