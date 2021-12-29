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
		String view = "reportFrm";
		
		mv = aServ.reportedReview(pageNum);
		
		return mv;
	}
	
	@GetMapping("delAdminMvReview")
	public String delAdminMvReview(int mvrnum,RedirectAttributes rttr) {
		
		/*int mvrnum = Integer.parseInt(mv_review);*/
		
		
		String view = aServ.delAdminMvReview(mvrnum,rttr);
		
		return view;
	}
	
	@GetMapping("sortByState")
	public ModelAndView sortByState(Integer pageNum) {
		
		mv = aServ.reportedReviewSort(pageNum);
		
		return mv;
	}
	@GetMapping("sortByStateDesc")
	public ModelAndView sortByStateDesc(Integer pageNum) {
		
		mv = aServ.reportedReviewSort(pageNum);
		
		return mv;
	}
}
