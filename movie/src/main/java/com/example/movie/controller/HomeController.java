package com.example.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
//	@Autowired
//	private MemberService mServ;
//	
//	@Autowired
//	private BusinessService bServ;
	
	// test 페이지 이동
	@GetMapping("test")
	public String test() {
		
		return "test";
	}
	
	@RequestMapping("/")
	public String index() {
		
		return "index";
	}
	
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
	
	@RequestMapping("movetestsehun")
	public String movetestsehun() {
		
		
		
		return "movetestsehun";
	}

}


