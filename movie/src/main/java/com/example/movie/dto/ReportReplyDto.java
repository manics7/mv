package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReportReplyDto {
	private int rp_rp_seq;
	private int rp_reply_no;
	private int review_num;
	private String rp_m_id;
	private String rpt_m_id;
	private String rp_why;
	private String rp_contents;
	private Timestamp rp_date;
	private int rp_state;
}
