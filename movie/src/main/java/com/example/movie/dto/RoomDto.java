package com.example.movie.dto;

import lombok.Data;

@Data
public class RoomDto {
	private int roomseq;//상영관 시퀀스
	private int roomno;//상영관 번호
	private int thcode;//영화관 코드
	private String roclass;//상영관 종류
	private String roname;//상영관 이름
	private int roomrow; //좌석 행
	private int roomcol; //좌석 열
	private int seatcnt;//총 좌석 수
	
	private String thname;//뷰의 영화관 이름
}
