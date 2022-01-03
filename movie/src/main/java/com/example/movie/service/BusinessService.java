package com.example.movie.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.RoomDto;
import com.example.movie.dto.SeatDto;
import com.example.movie.mapper.BusinessMapper;

import lombok.extern.java.Log;

@Service
@Log
public class BusinessService {

	@Autowired
	private BusinessMapper buMapper;
	
	private ModelAndView mv;
	
	//상영관 목록 가져오기
	public ModelAndView getRoomList() {
		mv = new ModelAndView();
		
		List<RoomDto> roomList = buMapper.getRoomList();
		
		mv.addObject("roomList", roomList);
		
		mv.setViewName("roomList");
		
		return mv;
	}

	//상영관 삭제하기
	@Transactional
	public String roomDelete(int roomseq, RedirectAttributes rttr) {
		String view = null;
		
		try {
			buMapper.RoomDelete(roomseq);
			
			view = "redirect:roomlist";
			rttr.addFlashAttribute("msg", "삭제 성공");
		} catch (Exception e) {
			view = "redirect:roomlist";
			rttr.addFlashAttribute("msg", "삭제 실패");
		}
		
		return view;
	}
	
	//상영관 등록 페이지 이동
	public ModelAndView roomInsertFrm() {
		mv = new ModelAndView();

		mv.setViewName("roomInsertFrm");

		return mv;
	}
	
	//상영관 등록 처리
	@Transactional
	public String roomInsert(HttpServletRequest request,
			RedirectAttributes rttr) {
		String view = null;
		String msg = null;
		/*
		int roomno = Integer.parseInt(multi.getParameter("roomno"));
		int thcode = Integer.parseInt(multi.getParameter("thcode"));
		String roclass = multi.getParameter("roclass");
		String roname = multi.getParameter("roname");
		int roomrow = Integer.parseInt(multi.getParameter("roomrow"));
		int roomcol = Integer.parseInt(multi.getParameter("roomcol"));
		int seatcnt = Integer.parseInt(multi.getParameter("seatcnt"));
		String seatno = multi.getParameter("seatno");
		
		RoomDto roDto = new RoomDto();
		roDto.setRoomno(roomno);
		roDto.setThcode(thcode);
		roDto.setRoclass(roclass);
		roDto.setRoname(roname);
		roDto.setRoomrow(roomrow);
		roDto.setRoomcol(roomcol);
		roDto.setSeatcnt(seatcnt);
		
		SeatDto seDto = new SeatDto();
		seDto.setThcode(thcode);
		seDto.setRoomno(roomno);
		//seDto.setSeatrow(roomrow);
		//seDto.setSeatcol(roomcol);
		//seDto.setSeatstat();
		seDto.setSeatno(seatno);
		*/
		int roomno = Integer.parseInt(request.getParameter("roomno"));
		int thcode = Integer.parseInt(request.getParameter("thcode"));
		String roclass = request.getParameter("roclass");
		String roname = request.getParameter("roname");
		int roomrow = Integer.parseInt(request.getParameter("roomrow"));
		int roomcol = Integer.parseInt(request.getParameter("roomcol"));
		int seatcnt = Integer.parseInt(request.getParameter("seatcnt"));
		String[] seatNoArray = request.getParameterValues("seatno");
		String[] seatNotArray = request.getParameterValues("seatNot");
		int col = 1;
		int row = 1;
		//배열 자르기
		String seatNoAll = seatNoArray[0];
		seatNoArray = seatNoAll.split(",");
		//배열 자르기
		String seatNotAll = seatNotArray[0];
		seatNotArray = seatNotAll.split(",");
		
		RoomDto roDto = new RoomDto();
		roDto.setRoomno(roomno);
		roDto.setThcode(thcode);
		roDto.setRoclass(roclass);
		roDto.setRoname(roname);
		roDto.setRoomrow(roomrow);
		roDto.setRoomcol(roomcol);
		roDto.setSeatcnt(seatcnt);
		
		try {
			buMapper.roomInsert(roDto);
			
			for(int i = 0; i <= (roomrow*roomcol)-1; i++) {
				SeatDto seDto = new SeatDto();
				seDto.setThcode(thcode);
				seDto.setRoomno(roomno);
		
				String seatNo = seatNoArray[i];
				seDto.setSeatno(seatNo);

				if(col <= roomcol) {
					seDto.setSeatcol(col);
					col++;
				}
				else {
					col=1;
					seDto.setSeatcol(col);
					col++;
					row++;
				}
				if(row <= roomrow) {
					seDto.setSeatrow(row);
				}
				
				for(int j = 0; j <= seatNotArray.length-1; j++) {
					String seatNot = seatNotArray[j];
					if(seatNo.equals(seatNot)) {
						seDto.setSeatstat(0);
						break;
					} else {
						seDto.setSeatstat(1);
					}
				}
				
				buMapper.seatInsert(seDto);
			}
			
			//buMapper.seatUpdate(seDto);
			view = "redirect:roomlist";
			msg = "상영관 등록 완료";
		} catch (Exception e) {
			view = "redirect:roomInsertFrm";
			msg = "상영관 등록 실패";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return view;
	}

}
