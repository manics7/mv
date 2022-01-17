package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class EventDto {
	private int event_num;
	private String b_id;
	private int th_code;
	private String event_title;
	private String event_content;
	private Timestamp event_start;
	private Timestamp event_end;
	private int event_target;
	private String event_category;
	private int event_sale;
}
