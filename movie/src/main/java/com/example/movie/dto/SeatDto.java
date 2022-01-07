package com.example.movie.dto;

import lombok.Data;

@Data
public class SeatDto {
	private int seat_seq;
	private int th_code;
	private int room_no;
	private int seat_row;
	private int seat_col;
	private int seat_status;
	private String seat_no;
	
	private String seatNot;
}
