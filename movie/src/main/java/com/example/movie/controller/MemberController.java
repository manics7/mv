package com.example.movie.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MemberDto;
import com.example.movie.service.MemberService;

import lombok.extern.java.Log;

@Controller
@Log
public class MemberController {

	@Autowired
	private MemberService mServ;
	
	// 이용자 회원가입
		@PostMapping("memberInsert")
		public String memberInsert(MemberDto member, RedirectAttributes rttr) {

			String view = mServ.memberInsert(member, rttr);
			
			return view;
		}
	
	// 이용자 회원가입 아이디 중복체크
	@GetMapping(value = "idCheck", produces = "application/text; charset=utf-8")
	@ResponseBody
	public String idCheck(String mid) {
		String res = mServ.idCheck(mid);
		
		return res;
	}
	
	// 이용자 로그인
	@PostMapping("loginProc")
	public String loginProc(MemberDto member, RedirectAttributes rttr) {
		String view = mServ.loginProc(member, rttr);
		
		return view;
	}
	
	// 이용자 로그아웃
	@GetMapping("logout")
	public String logout() {
		
		String view = mServ.logout();
		
		return view;
	}
	
}
