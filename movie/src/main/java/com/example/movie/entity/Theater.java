package com.example.movie.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@DynamicInsert // null 컬럼 입력 제외
@DynamicUpdate  // null 컬럼 업데이트 제외
@Entity
@NoArgsConstructor // 기본생성자 생성
@ToString // toString() 함수 자동생성
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "THEATER")
public class Theater {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="TH_CODE", columnDefinition="영화관코드")
	private Integer thCode;
	
	@Column(name="B_ID", columnDefinition="사업자, ID")
	private String bId;
	
	@Column(name="TH_NAME", columnDefinition="영화관이름")
	private String thName;
	
	@Column(name="TH_LOGO", columnDefinition="로고 이미지")
	private String thLogo;
	
	@Column(name="TH_LOCATION", columnDefinition="위치정보")
	private String thLocation;
	
	@Column(name="TH_INTRODUCE", columnDefinition="영화관소개")
	private String thIntroduce;	
	
	@Column(name="TH_AREACODE", columnDefinition="시도코드")
	private String thAreacode;	
	
	@Column(name="TH_PARKING", columnDefinition="주차안내")
	private String thParking;
	
	@Column(name="TH_IMAGE1", columnDefinition="영화관 사진")
	private String thImage1;
	
	@Column(name="TH_IMAGE2", columnDefinition="영화관 사진")
	private String thImage2;
	
	@Column(name="TH_IMAGE3", columnDefinition="영화관 사진")
	private String thImage3;
	
	@Column(name="TH_TEL", columnDefinition="전화번호")
	private String thTel;

}
