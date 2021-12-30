package com.example.movie.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Question_File")
public class QuestionFile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="QUES_FILE_NO", columnDefinition="문의사항번호")
	private Integer quesFileNo;
	@Column(name="QUES_NO", columnDefinition="회원아이디")
	private Integer quesNo;
	@Column(name="QUES_FILE_PRENAME", columnDefinition="제목")
	private String quesFileTitle;
	@Column(name="QUES_FILE_NEWNAME", columnDefinition="문의내용")
	private String questFileCont;
		
	
	
}
