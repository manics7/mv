package com.example.movie.dto;

import lombok.Data;

@Data
public class TheaterDto {
	private int th_code;//영화관 코드
	private String b_id;//사업자 아이디
	private String th_name;//영화관이름
	private String th_logo;//로고 사진
	private String th_location;//영화관 주소
	private String th_introduce;//영화관 소개
	private String th_areacode;//지역 코드
	private String th_parking;
	private String th_image;
	private String th_tel;
}
