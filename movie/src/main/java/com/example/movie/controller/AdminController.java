package com.example.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
	@GetMapping("/requeboard_read")
	public ModelAndView requeboardRead(String ques_title) {
	ModelAndView mv = new ModelAndView();
	System.out.println("ques_title = "+ques_title);
		
	List<quesboardDto> readqlist = aServ.getboardRead(ques_title);
	
	mv.addObject("qrlist", readqlist);
	mv.setViewName("requeboard_read");
	System.out.println("readqlist = "+readqlist);
		return mv;
	}

	
	
	
}
