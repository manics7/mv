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
	private String mId;
		
	@Column(name="M_PW", columnDefinition="비밀번호")
	private String mPw;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="M_BIRTH", columnDefinition="생년월일")
	private Date mBirth;
	
	@Column(name="M_TEL", columnDefinition="전화번호")
	private String mTel;
	
	@Column(name="M_LIKE", columnDefinition="즐겨찾는영화관")
	private String mLike;
	
	@Column(name="M_EMAIL", columnDefinition="이메일")
	private String mEmail;
	
	@Column(name="M_NAME", columnDefinition="이름")
	private String mName;
	
	@Column(name="M_GENDER", columnDefinition="성별")
	private String mGender;
	
	@Column(name="M_POINT", columnDefinition="포인트")
	private Integer mPoint;
	
	@Column(name="M_GRADE", columnDefinition="등급")
	private Integer mGrade;
	
	@Column(name="M_WARNING", columnDefinition="경고횟수")
	private Integer mWarning;
	
}
