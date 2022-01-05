package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.BusinessDto;
import com.example.movie.service.BusinessService;

import lombok.extern.java.Log;

@Controller
@Log
public class BusinessContorller {
	
	@Autowired
	private BusinessService buServ;
	
	private ModelAndView mv;
	
	// 사업자 회원가입
		@PostMapping("businessInsert")
		public String businessInsert(BusinessDto business, RedirectAttributes rttr) {
			
			String view = buServ.businessInsert(business, rttr);
			
			return view;
		}
	
	// 사업자 회원가입 아이디 중복체크
	@GetMapping(value = "buIdCheck", produces = "application/text; charset=utf-8")
	@ResponseBody
	public String buIdCheck(String bid) {
		String res = buServ.buIdCheck(bid);
		
		return res;
	}

	// 사업자 로그인
	@PostMapping("bu_loginProc")
	public String bu_loginProc(BusinessDto business, RedirectAttributes rttr) {
		
		String view = buServ.bu_loginProc(business, rttr);
		
		return view;
	}
	
	// 사업자 메인 페이지 이동
	@GetMapping("businessPage")
	public ModelAndView businessPage() {
		
		mv = buServ.businessPage();
		
		return mv;
	}
	@GetMapping("businessUpdateFrm")
	public String businessUpdateFrm() {
		String view = "businessUpdateFrm";
		
		return view;
	}
	////미완
	@GetMapping("businessUpdateProc")
	public String businessUpdateProc() {
		String view = "businessPage";
		
		return view;
	}
	
	// 사업자 로그아웃
	@GetMapping("bu_logout")
	public String bu_logout() {
		
		String view = buServ.bu_logout();
		
		return view;
	}
	
	// test영화 등록
	@PostMapping("insertMovie")
	public String insertMovie(MultipartHttpServletRequest multi, RedirectAttributes rttr) {
		
		String view = buServ.insertMovie(multi, rttr);
		
		return view;
	}
	
} // class end
