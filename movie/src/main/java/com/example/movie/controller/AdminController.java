package com.example.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.quesboardDto;
import com.example.movie.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	public AdminService aServ;
	public ModelAndView mv;
	
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
//	@GetMapping("/requeboard_read")
//	public ModelAndView requeboardRead(String ques_title) {
//		ModelAndView mv = new ModelAndView();
//		System.out.println("ques_title = "+ques_title);
//
//		List<quesboardDto> readqlist = aServ.getboardRead(ques_title);
//
//		mv.addObject("qrlist", readqlist);
//		mv.setViewName("requeboard_read");
//		System.out.println("readqlist = "+readqlist);
//		return mv;
//	}
	
	
	@GetMapping("/requeboard_read")
	public ModelAndView requeboardRead(int ques_no,Integer view) {
		
		int num = (view == null)? 0 : view;
		
		ModelAndView mv = new ModelAndView();
		System.out.println("ques_no = "+ques_no);

		List<quesboardDto> readqlist = aServ.getboardRead(ques_no);

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

	
	//1대1 문의글 답변
	@GetMapping("quesboard_rewrite")
	public String quesboard_rewrite() {
		
		return "quesboard_rewrite";
	}
	
	//사업자회원 정보 출력 
	@GetMapping("getBulist")
	public ModelAndView getbulist(Integer pageNum) {
		System.out.println("Business pageNum = "+pageNum);
		ModelAndView mv = new ModelAndView();
		//사업자 정보 인출
		List<BusinessDto> busList = aServ.getbulist(pageNum);
		
		//페이징처리
		String pageHtml = aServ.busgetpaging(pageNum);
		
		//mv에 데이터 투입
		mv.addObject("paging", pageHtml);
		mv.addObject("busList", busList);
		mv.setViewName("mmanageBu");
		
		return mv;
	}



}
