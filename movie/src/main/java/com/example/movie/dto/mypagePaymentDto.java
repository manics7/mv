package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class mypagePaymentDto {
	
	private String mvname;
	private String thname;
	private String rsrv_no;
	private int amount;
	private Timestamp approved_at;
}
