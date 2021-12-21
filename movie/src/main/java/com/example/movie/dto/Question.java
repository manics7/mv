package com.example.movie.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Question {

	@Id
	@Column(name="QUES_NO", columnDefinition="문의사항번호")
	private Integer quesNo;
	@Column(name="M_ID", columnDefinition="회원아이디")
	private String mId;
	@Column(name="QUES_TITLE", columnDefinition="제목")
	private String quesTitle;
	@Column(name="QUES_CONT", columnDefinition="문의내용")
	private String questCont;
	@Column(name="QUES_STATE", columnDefinition="상태")
	private Integer questSate;	
	/*
	 * 서버 Get 요청 @ModelAttribute에는 @DateTimeFormat 사용 서버 Post 요청 
	 * @RequestBody에는 JSON 객체를 @DateTimeFormat과 @JsonFormat 로 모두 사용
	 *  2개의 어노테이션 모두가 있으면 선언 시에는 @JsonFormat이 먼저 적용 서버 응답 시 ResponseBody에서는 @JsonFormat 만 적용
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="QUES_DATE", columnDefinition="질문일자")
	private Date quesDate;


	
}
