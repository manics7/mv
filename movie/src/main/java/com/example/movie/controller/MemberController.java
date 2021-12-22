package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MemberDto;
import com.example.movie.service.MemberService;

import lombok.extern.java.Log;

@Controller
@Log
public class MemberController {

	@Autowired
	private MemberService mServ;
	
	// 이용자 로그인
	@PostMapping("loginProc")
	public String loginProc(MemberDto member, RedirectAttributes rttr) {
		log.info("loginProc()");
		String view = mServ.loginProc(member, rttr);
		
		return view;
	}
	
}
