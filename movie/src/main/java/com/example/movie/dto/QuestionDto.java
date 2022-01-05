package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class QuestionDto {
	private int ques_no;
	private String m_id;
	private String ques_title;
	private String ques_cont;
	private int ques_state; 
	private Timestamp ques_date;
}
