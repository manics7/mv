package com.example.movie.dto;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Table(name = "NOTICE")
@Entity
@NoArgsConstructor
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="NOTICE_NO", columnDefinition="글번호")
	private Integer noticeNo;
	
	@NonNull
	@Column(name="NOTICE_TITLE", columnDefinition="제목")
	private String noticeTitle;
	
	@NonNull
	@Column(name="NOTICE_CONTENT", columnDefinition="공지내용")
	private String noticeContent;
	
	@Column(name="REG_DATE", columnDefinition="작성일")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date regDate;
	
	@Column(name="VIEWS_CNT", columnDefinition="조회수")
	private Integer viewCnt;	
	
	@NonNull
	@Column(name="NOTICE_CLASS", columnDefinition="구분")
	private Integer noticeClass;


	
}
