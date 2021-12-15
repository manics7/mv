package com.example.movie.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.service.EmpService;

@Controller
public class TestController {
	
	@Autowired
	private EmpService empService;
	
	@RequestMapping("/test") public ModelAndView test() throws Exception{
		ModelAndView mv = new ModelAndView("test"); 
		
		//mv = empService.getEmpList();
		//mv = empService.getBounusList();
		//mv = empService.getEmpList2();
		mv = empService.getEmp();
		return mv; 
	}
	
	@RequestMapping("/test2") public ModelAndView test2() throws Exception{
		ModelAndView mv = new ModelAndView("test"); 
		
		//mv = empService.getEmpList();
		mv = empService.getBounusList();
		//mv = empService.getEmpList2();
		
		return mv; 
	}
	
	@RequestMapping("/test3") public ModelAndView transactiontest() throws Exception{
		ModelAndView mv = new ModelAndView("test"); 
		
		//empService.jpaInsertTest();
		empService.insertMybatis();
		
		return mv; 
	}


}
