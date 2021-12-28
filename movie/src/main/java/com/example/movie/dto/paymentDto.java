package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class paymentDto {
	private String tid;
	private String rsrv_no;
	private String cid;
	private String status;
	private String payment_method_type;
	private String amount;
	private String quantity;
	private Timestamp qpproved_at;
}
