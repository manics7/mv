package com.example.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.dto.MovieOfficialDto;
import com.example.movie.dto.TheaterDto;
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
	
	
	
	@GetMapping("/")
	public ModelAndView index() {
		
		mv = mServ.boxOffice();
		return mv;
	}

	//영화관 찾기 th_name 및 th_code 출력 
	@ResponseBody
	@GetMapping("searchTheater")
	public List<TheaterDto> searchTheater() {
List<TheaterDto> thCodeList = mServ.selectThcode();
//List<MovieOfficialDto> mvOffList = mServ.seletMvOffList();

		return thCodeList;
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

}