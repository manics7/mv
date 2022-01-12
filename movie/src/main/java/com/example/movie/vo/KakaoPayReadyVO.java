package com.example.movie.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KakaoPayReadyVO {
	
	//response 
	private String tid; //결제 고유 번호, 20자
	private String next_redirect_pc_url;// 요청한 클라이언트가 pc 웹일 경우 redirect. 카카오톡으로 TMS를 보내기 위한 사용자입력화면이으로 redirect 
	private Date created_at;//결제 준비 요청시간


}
