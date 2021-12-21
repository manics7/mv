package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.BusinessDto;
import com.example.movie.service.BusinessService;

@Controller
public class BusinessContorller {
	
	@Autowired
	private BusinessService bServ;
	
	// 사업자 로그인
	@PostMapping("bu_loginProc")
	public String bu_loginProc(BusinessDto business, RedirectAttributes rttr) {
		
		String view = bServ.bu_loginProc(business, rttr);
		
		return view;
	}
	
}
