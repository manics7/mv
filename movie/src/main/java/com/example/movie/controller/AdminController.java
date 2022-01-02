package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService aServ;
	
	private ModelAndView mv;
	
	// 현재상영작 목록 페이지 이동(현재상영작 불러오기)
	@GetMapping("currentMovieList")
	public ModelAndView currentMovieList() {
		
		mv = aServ.getMovieList();
		
		return mv;
	}
	
}
