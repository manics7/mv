package com.example.movie.dto;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@DynamicInsert // null 컬럼 입력 제외
@DynamicUpdate  // null 컬럼 업데이트 제외
@Entity
@NoArgsConstructor // 기본생성자 생성
@ToString // toString() 함수 자동생성
public class Member {
	
	@Id
	@Column(name="M_ID", columnDefinition="아이디")
	private String m_id;
	
	@Column(name="M_PW", columnDefinition="비밀번 호")
	private String m_pw;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="M_BIRTH", columnDefinition="생년월일")
	private Date m_birth;
	
	@Column(name="M_TEL", columnDefinition="전화번호")
	private String m_tel;
	
	@Column(name="M_LIKE", columnDefinition="즐겨찾는영화관")
	private String m_like;
	
	@Column(name="M_EMAIL", columnDefinition="이메일")
	private String m_email;
	
	@Column(name="M_NAME", columnDefinition="이름")
	private String m_name;
	
	@Column(name="M_GENDER", columnDefinition="성별")
	private String m_gender;
	
	@Column(name="M_POINT", columnDefinition="포인트")
	private Integer m_point;
	
	@Column(name="M_GRADE", columnDefinition="등급")
	private Integer m_grade;
	
	@Column(name="M_WARNING", columnDefinition="경고횟수")
	private Integer m_warning;
	
}
