package com.example.movie.entity;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
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
	
	/*
	 * 서버 Get 요청 @ModelAttribute에는 @DateTimeFormat 사용 
	 * 서버
	 *  Post 요청 @RequestBody에는 JSON 객체를 @DateTimeFormat과 @JsonFormat 로 모두 사용
	 *  2개의 어노테이션 모두가 있으면 선언 시에는 @JsonFormat이 먼저 적용 서버 응답 시 ResponseBody에서는 @JsonFormat 만 적용
	 */
	@Column(name="REG_DATE", columnDefinition="작성일")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd")	
	//날짜타입(java.util.Date, java.util.Calendar)을 매핑할때 사용.
	//자바8에서 지원하는 LocalDate, LocalDateTime을 사용할때는 생략 가능(하이버네이트 지원)
	//@Temporal(TemporalType.DATE) 
	private LocalDate regDate;
	
	@Column(name="VIEW_CNT", columnDefinition="조회수")
	private Integer viewCnt;	
	
	@NonNull
	@Column(name="NOTICE_CLASS", columnDefinition="구분")
	private Integer noticeClass;

	@Builder
    public Notice(Integer noticeNo, String noticeTitle, String noticeContent, LocalDate regDate
    					,Integer viewCnt, Integer noticeClass, String noticeClassName) {
        this.noticeNo = noticeNo;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.regDate = regDate;
        this.viewCnt = viewCnt;        
    }

	@PrePersist
	public void regDate() {
		if(this.regDate == null) {
			this.regDate = LocalDate.now();
		}		
	}
	
}
