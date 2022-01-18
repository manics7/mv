package com.example.movie.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ScheduleDto {

	private int sch_code;
	private String movie_cd;
	private int th_code;
	private int room_no;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sch_date;
	private int sch_time;
}
