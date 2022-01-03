package com.example.movie.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.service.BusinessService;

import lombok.extern.java.Log;

@Controller
@Log
public class BusinessContorller {
	
	@Autowired
	private BusinessService buServ;
	
	private ModelAndView mv;
	
	//상영관 목록 이동
	@GetMapping("roomlist")
	public ModelAndView roomList() {
		mv = buServ.getRoomList();
		
		return mv;
	}
	
	//상영관 삭제
	@GetMapping("roomDelete")
	public String roomDelete(int roomseq,
			RedirectAttributes rttr) {
		String view = buServ.roomDelete(roomseq, rttr);
		
		return view;
	}
	
	//상영관 등록 페이지 이동
	@GetMapping("roomInsertFrm")
	public ModelAndView roomInsertFrm() {
		mv = buServ.roomInsertFrm();
		
		return mv;
	}
	
	//상영관 등록 처리
	@PostMapping("roomInsert")
	//public String roomInsert(MultipartHttpServletRequest multi,
	public String roomInsert(HttpServletRequest request,
			RedirectAttributes rttr) {
		//String view = buServ.roomInsert(multi, rttr);
		String view = buServ.roomInsert(request, rttr);
		
		return view;
	}
	
} //class end