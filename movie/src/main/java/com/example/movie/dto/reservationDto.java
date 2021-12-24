package com.example.movie.dto;

import lombok.Data;

@Data
public class reservationDto {
	private String rsrv_no;
	private String sch_no;
	private String m_id;
	private String rsrv_date;
	private int adult_cnt;
	private int youth_cnt;
	private int price;
}
