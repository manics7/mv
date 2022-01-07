package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReportReviewDto {
	private int rp_rv_seq; //시퀀스
	private int review_num; //게시글 번호
	private String rp_m_id; //신고자 아이디
	private String rpt_m_id; //신고 당한 아이디
	private String rp_why; //신고 사유
	private Timestamp rp_date; //신고 날짜
	private int rp_state; //처리 상태
}
