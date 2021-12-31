package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.service.AdminService;

import java.util.List;


import com.example.movie.dto.quesboardDto;
import com.example.movie.mapper.AdminMapper;


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

	@GetMapping("/quesboard")
	public ModelAndView quesboard(Integer pageNum) {
		ModelAndView mv = new ModelAndView();

		List<quesboardDto> qList = aServ.getQboardList(pageNum);

		mv.addObject("qlist", qList);

		//페이징 처리.
		String pageHtml = aServ.getpaging(pageNum);

		mv.addObject("paging", pageHtml);


		mv.setViewName("quesboard");

		System.out.println("qlist = "+qList);
		return mv;
	}
	@GetMapping("/requeboard_read")
	public ModelAndView requeboardRead(int ques_no,Integer view) {
		
		int num = (view == null)? 0 : view;
		
		ModelAndView mv = new ModelAndView();
		System.out.println("ques_no = "+ques_no);

		List<quesboardDto> readqlist = aServ.getboardRead(ques_no);
		
		quesboardDto qDto = readqlist.get(0);
		
		int state = qDto.getQues_state();
		/*
		if(state == 1) {
			quesReplyDto qrDto = aServ.selectQuesReply(ques_no);
			
			mv.addObject("reply",qrDto);
		}
		*/
		
		mv.addObject("qrlist", readqlist);
		if(num == 0) {
			mv.setViewName("requeboard_read");
		}
		else {
			mv.setViewName("questionContents");
		}
		System.out.println("readqlist = "+readqlist);
		return mv;
	}
	@GetMapping("quesboard_rewrite")
	public String quesboard_rewrite() {
		
		return "quesboard_rewrite";
	}

}
