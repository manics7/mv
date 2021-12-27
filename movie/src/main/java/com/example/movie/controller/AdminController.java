package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.service.AdminService;


@Controller
public class AdminController {

	
	@Autowired
	private AdminService aServ;
	
	private ModelAndView mv;
	
	@GetMapping("reportFrm")
	public ModelAndView reportFrm(Integer pageNum) {
		
		mv = aServ.reportedReview(pageNum);
		
		return mv;
	}
	
	@GetMapping("deleteMvReview")
	public String deleteMvReview (int mvrNum,RedirectAttributes rttr) {
		
		String view = aServ.deleteMvReview(mvrNum,rttr);
		
		return view;
	}
}
