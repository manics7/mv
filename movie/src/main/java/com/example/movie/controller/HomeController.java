package com.example.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.service.MemberService;

@Controller
public class HomeController {
	
	private ModelAndView mv;
	private MemberService mServ;
	
	@RequestMapping("/")
	public String index() {
		
		
		
		return "index";
	}
	
}
