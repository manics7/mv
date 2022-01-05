package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class quesReplyDto {
	private int ques_reply_no;
	private int ques_no;
	private String ques_reply_cont;
	private Timestamp ques_reply_date;
	private int ques_reply_state;
}
