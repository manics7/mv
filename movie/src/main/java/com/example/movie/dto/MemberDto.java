
package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberDto {
	private String m_id;
	private String m_pw;
	private Timestamp m_birth;
	private String m_tel;
	private String m_like;
	private String m_email;
	private String m_name;
	private int m_gender;
	private int m_point;
	private int m_grade;
	private int m_warning;

	
}