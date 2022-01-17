package com.example.movie.dto;


import java.util.Date;

import lombok.Data;

@Data
public class ScheduleDetailDto {

	private int sch_detail_seq;
	private int sch_code;
	private Date sch_detail_start;
	private Date sch_detail_end;
	
}
