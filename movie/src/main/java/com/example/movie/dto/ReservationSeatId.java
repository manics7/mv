package com.example.movie.dto;


import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationSeatId implements Serializable{

	private String rsrvNo;
	
	private Integer seatNo;
	

	
	
}
