package com.example.movie.mapper;

import java.util.List;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.RoomDto;
import com.example.movie.dto.SeatDto;

public interface BusinessMapper  extends MybatisMapper {
	//상영관 목록 가져오기
	public List<RoomDto> getRoomList();
	//상영관 삭제하기
	public boolean RoomDelete(Integer roomseq);
	//상영관 등록하기
	public void roomInsert(RoomDto roDto);
	//좌석정보 등록하기
	public void seatInsert(SeatDto seDto);
	//좌석정보 업데이트
	//public boolean seatUpdate(SeatDto seDto);
}
