package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class reservationDto {
	private int rsrv_no;
	private int sch_no;
	private String m_id;
	private Timestamp rsrv_date;
	private int adult_cnt;
	private int youth_cnt;
	private int price;
}
