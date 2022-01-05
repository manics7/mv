package com.example.movie.dto;

import lombok.Data;

@Data
public class RoomDto {
	
	private int room_seq; //상영관 시퀀스
	private int th_code; //영화관 코드
	private String room_class; //상영관 종류
	private String room_name; //상영관 이름
	private int room_row; //전체 열
	private int room_col; //전체 행
	private int seat_cnt; //총 좌석수
	private int room_no; //상영관 번호
	
}
