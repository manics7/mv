package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class reservationDto {
	private int rsrv_no;
	private int sch_code;
	private String m_id;
	private Timestamp rsrv_date;
	private int adult_cnt;
	private int youth_cnt;
	private int price;
	private int sch_detail_seq;
	private int rsrv_status;
	
	private String mvname;
	private String thname;
	private Timestamp sch_date;
}
