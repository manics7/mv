package com.example.movie.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Question_File")
public class QuestionFile {

	@Id
	@Column(name="QUESFILE_NO", columnDefinition="문의사항번호")
	private Integer quesNo;
	@Column(name="QUES_NO", columnDefinition="회원아이디")
	private String mId;
	@Column(name="QUESFILE_PRENAME", columnDefinition="제목")
	private String quesTitle;
	@Column(name="QUESFILE_NEWNAME", columnDefinition="문의내용")
	private String questCont;
		
	
	
}
