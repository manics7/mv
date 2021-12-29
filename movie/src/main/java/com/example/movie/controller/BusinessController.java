package com.example.movie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.service.BusinessService;

@Controller
public class BusinessController {
	
	private static final Logger logger =
			LoggerFactory.getLogger(BusinessController.class);
	
	@Autowired
	private BusinessService buServ;
	
	//영화관 등록폼
	@GetMapping("thaddFrm")
	public String thaddFrm() {
		logger.info("thaddFrm()");
		
		return "thaddFrm";
	}
	
	//영화관 등록
	@PostMapping("theaterAdd")
	public String theaterAdd(MultipartHttpServletRequest multi, RedirectAttributes rttr) {
		logger.info("theaterAdd()");
		
		String view = buServ.theaterInsert(multi, rttr);
		
		return view;
	}
	
	//영화관 목록 페이지
	@GetMapping("theater")
	public String theater() {
		logger.info("theater()");
		
		return "theater";
	}
}
