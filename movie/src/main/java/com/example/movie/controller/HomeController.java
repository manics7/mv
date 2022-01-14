package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.service.BusinessService;
import com.example.movie.service.MemberService;

import lombok.extern.java.Log;

@Controller
@Log
public class HomeController {
	
	@Autowired
	private MemberService mServ;
	
	@Autowired
	private BusinessService bServ;
	
	
	private ModelAndView mv;
	
	@RequestMapping("/")
	public ModelAndView index() {
		
		ModelAndView mv = mServ.selectThcode();
		
		mv.setViewName("index");
		//thCodeList
		return mv;
	}
	
//	@GetMapping("searchTheater")
//	public ModelAndView searchTheater() {
//ModelAndView mv = mServ.selectThcode();
//		
//		mv.setViewName("index");
//		
//		return mv;
//	}
//	
	
	// 이용자 회원가입 페이지 이동
	@GetMapping("joinFrm")
	public String joinFrm() {
		
		return "joinFrm";
	}
	
	// 사업자 회원가입 페이지 이동
	@GetMapping("bu_joinFrm")
	public String bu_joinFrm() {
		
		return "bu_joinFrm";
	}

	// 영화 상세 페이지 이동
	@GetMapping("movieDetail")
	public ModelAndView movieDetail(String movie_cd) {
		
		mv = mServ.movieDetail(movie_cd);
		
		return mv;
	}
	
}