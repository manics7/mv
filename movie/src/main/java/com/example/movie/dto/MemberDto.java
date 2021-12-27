package com.example.movie.dto;

import lombok.Data;

@Data
public class MemberDto {

	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_tel;
	private String m_email;
	private String m_birth;
	private int m_gender;
	private int m_like;	
	
}